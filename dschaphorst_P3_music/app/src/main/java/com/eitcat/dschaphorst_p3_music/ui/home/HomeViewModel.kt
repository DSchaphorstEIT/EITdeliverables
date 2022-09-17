package com.eitcat.dschaphorst_p3_music.ui.home

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.lifecycle.*
import com.eitcat.dschaphorst_p3_music.data.api.ApiHelper
import com.eitcat.dschaphorst_p3_music.data.database.MusicDB
import com.eitcat.dschaphorst_p3_music.data.database.LocalRepo
import com.eitcat.dschaphorst_p3_music.data.model.Song
import com.eitcat.dschaphorst_p3_music.data.model.mapToSongList
import com.eitcat.dschaphorst_p3_music.util.ConnectivityState
import com.eitcat.dschaphorst_p3_music.util.FailureResponseFromServer
import com.eitcat.dschaphorst_p3_music.util.NullResponseFromServer
import com.eitcat.dschaphorst_p3_music.util.UIState
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

private const val TAG = "HomeViewModel"

/**
 * The primary ModelView used to perform the majority of the business logic, and is used
 * by multiple fragments.
 *
 * @constructor
 * Creates the data to be passed between fragments.
 *
 * @param application The application containing the fragments that are accessing this ViewModel
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: LocalRepo
    private val _musicStatus: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val musicStatus: LiveData<UIState> get() = _musicStatus
    var networkState: ConnectivityState
    var curSong: Song? = null
    var songHistList: List<Song> = emptyList()

    val player : ExoPlayer by lazy {
        ExoPlayer.Builder(application.baseContext).build()
    }

    /**
     * Create a musicDAO for the repository to access and set the networkState
     */
    init {
        val musicDAO = MusicDB.getDB(application).getMusicDAO()
        repo = LocalRepo(musicDAO)
        networkState = ConnectivityState(application.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager)
    }

    /**
     * Conditional access method used to pull the music data from the iTunes Api calls. Launches
     * coroutine and emits UIStates for loading, success, or error.
     *
     * @param term The term that is used to search through the Api. Uses default value if nothing passed.
     */
    fun pullMusicData(term : String = ""){
        viewModelScope.launch(Dispatchers.IO) {
            val flowHolder: Flow<UIState> = flow {
                emit(UIState.LOADING)
                try {
                    val response = if(term == "") ApiHelper.serviceApi.getMusic() else ApiHelper.serviceApi.getMusic(term)
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.d(TAG, "pullMusicData: ${it.songData?.first()?.artistName ?: "No Data"}")
                        emit(UIState.SUCCESS(it.songData.mapToSongList()))
                        } ?: throw NullResponseFromServer("Songs are null")
                    } else {
                        throw FailureResponseFromServer(response.errorBody()?.string())
                    }
                } catch (e : Exception){
                    emit(UIState.ERROR(e))
                    Log.e(TAG, "Caught Error: ${e.localizedMessage}", e)
                }
            }
            flowHolder.collect {
                withContext(Dispatchers.Main){
                    // Change to main thread
                }
                _musicStatus.postValue(it)
            }
        }
    }

    /**
     * Add song to the player's queue. If no song provided, adds the most recently viewed song.
     *
     * @param song THe song to add to queue. Default: curSong
     */
    fun addSongToQueue(song: Song? = curSong){
        if (song != null) {
            player.addMediaItem(MediaItem.fromUri(song.previewUrl))
        }
    }

    /**
     * Checks if the player is not already playing, and starts it.
     *
     */
    fun play(){
        if(!player.isPlaying) {
            player.prepare()
            player.play()
        }
    }

    /**
     * Adds most recent song list to the repository. Only should need to be called when
     * a network connection is lost and thus Api calls will not be possible.
     *
     */
    fun addCurrentToDatabase(){
        repo.insertSongs(songHistList)
        repo.localMusicData = repo.getMusicData()
    }

    /**
     * Get the music stored in the local database.
     *
     * @return
     */
    fun getFromDatabase(): LiveData<List<Song>> = repo.localMusicData
}