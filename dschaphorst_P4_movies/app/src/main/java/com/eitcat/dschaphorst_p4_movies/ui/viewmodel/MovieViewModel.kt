package com.eitcat.dschaphorst_p4_movies.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.*
import com.eitcat.dschaphorst_p4_movies.data.api.MovieApi
import com.eitcat.dschaphorst_p4_movies.data.model.Movie
import com.eitcat.dschaphorst_p4_movies.data.model.Video
import com.eitcat.dschaphorst_p4_movies.data.model.nullChecker
import com.eitcat.dschaphorst_p4_movies.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MovieViewModel"

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val _movieLoadStatus: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val movieLoadStatus: LiveData<UIState> get() = _movieLoadStatus
    val networkStatus: ConnectivityState
    val availableApiCalls: List<String> // Consider moving this into the API
    var curMovie: Movie? = null
    var movieHistList: List<Movie> = emptyList()
    var videoHistList: List<Video> = emptyList()

    init {
        networkStatus = ConnectivityState(application
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        availableApiCalls = listOf("popular", "now_playing", "upcoming")
    }

    fun pullMovieData(caller: String = availableApiCalls[0]){
        viewModelScope.launch(Dispatchers.IO) {
            val flowHolder: Flow<UIState> = flow {
                emit(UIState.LOADING)
                try {
                    val response = MovieApi.serviceApi.getMovie(queryType = caller)
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(UIState.SUCCESS(movies = it.results.nullChecker()))
                        } ?: throw NullResponseFromServer("Movies are null")
                    } else {
                        throw FailureResponseFromServer(response.errorBody()?.string())
                    }
                } catch (e: Exception){
                    emit(UIState.ERROR(e))
                    Log.e(TAG, "Caught Error: ${e.localizedMessage}", e)
                }
            }
            flowHolder.collect {
                withContext(Dispatchers.Main){
                    // Change to main thread
                }
                _movieLoadStatus.postValue(it)
            }
        }
    }

    fun pullVideoData(){
        viewModelScope.launch(Dispatchers.IO) {
            val flowHolder: Flow<UIState> = flow {
                emit(UIState.LOADING)
                try {
                    val response = MovieApi.serviceApi.getVideo(
                        movieID = curMovie?.id ?: throw InvalidApiCaller("Invalid Movie")
                    )
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.d(TAG, "pullVideoData: ${it.results}")
                            emit(UIState.SUCCESS(videos = it.results.nullChecker()))
                        } ?: throw NullResponseFromServer("Videos are null")
                    } else {
                        throw FailureResponseFromServer(response.errorBody()?.string())
                    }
                } catch (e: Exception){
                    emit(UIState.ERROR(e))
                    Log.e(TAG, "Caught Error: ${e.localizedMessage}", e)
                }
            }
            flowHolder.collect {
                withContext(Dispatchers.Main){
                    // Change to main thread
                }
                _movieLoadStatus.postValue(it)
            }
        }
    }

    fun openYoutube(context: Context, video: Video) {
        val url = "https://www.youtube.com/watch?v=" + video.key
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(context, intent, null)
    }
}