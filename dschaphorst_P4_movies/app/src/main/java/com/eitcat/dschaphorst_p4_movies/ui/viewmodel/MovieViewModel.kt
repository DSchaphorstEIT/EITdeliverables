package com.eitcat.dschaphorst_p4_movies.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.*
import com.eitcat.dschaphorst_p4_movies.data.api.MovieApi
import com.eitcat.dschaphorst_p4_movies.data.model.Movie
import com.eitcat.dschaphorst_p4_movies.data.model.nullChecker
import com.eitcat.dschaphorst_p4_movies.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MovieViewModel"

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val _movieLoadStatus: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val movieLoadStatus: LiveData<UIState> get() = _movieLoadStatus
    val networkStatus: ConnectivityState
    val availableApiCalls: List<String>
    var curMovie: Movie? = null
    var movieHistList: List<Movie> = emptyList()

    init {
        networkStatus = ConnectivityState(application
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        availableApiCalls = listOf("POPULAR", "NOW_PLAYING", "COMING_SOON")
    }

    fun pullMovieData(caller: String = availableApiCalls[0]){
        viewModelScope.launch(Dispatchers.IO) {
            val flowHolder: Flow<UIState> = flow {
                emit(UIState.LOADING)
                try {
                    val response = when (caller) {
                        availableApiCalls[0] -> MovieApi.serviceApi.getMoviePop()
                        availableApiCalls[1] -> MovieApi.serviceApi.getMovieNow()
                        availableApiCalls[2] -> MovieApi.serviceApi.getMovieSoon()
                        else -> throw InvalidApiCaller(
                            "Given an invalid API call. Either change caller, or create a new API calling method for case: $caller"
                        )
                    }
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(UIState.SUCCESS(it.results.nullChecker()))
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

    private val _text = MutableLiveData<String>().apply {
        value = "This Fragment has not yet been implemented"
    }
    val text: LiveData<String> = _text
}