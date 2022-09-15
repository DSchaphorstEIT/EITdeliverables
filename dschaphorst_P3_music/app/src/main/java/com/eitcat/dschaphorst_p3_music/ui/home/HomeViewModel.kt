package com.eitcat.dschaphorst_p3_music.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.eitcat.dschaphorst_p3_music.data.api.ApiHelper
import com.eitcat.dschaphorst_p3_music.data.database.MusicDB
import com.eitcat.dschaphorst_p3_music.data.database.LocalRepo
import com.eitcat.dschaphorst_p3_music.data.model.Song
import com.eitcat.dschaphorst_p3_music.data.model.mapToSongList
import com.eitcat.dschaphorst_p3_music.util.FailureResponseFromServer
import com.eitcat.dschaphorst_p3_music.util.NullResponseFromServer
import com.eitcat.dschaphorst_p3_music.util.UIState
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

private const val TAG = "HomeViewModel"

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: LocalRepo
    private val _musicStatus: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val musicStatus: LiveData<UIState> get() = _musicStatus
    var curSong: Song? = null

    init {
        val musicDAO = MusicDB.getDB(application).getMusicDAO()
        repo = LocalRepo(musicDAO)
    }

    fun pullMusicData(){
        viewModelScope.launch(Dispatchers.IO) {
            val flowHolder: Flow<UIState> = flow {
                emit(UIState.LOADING)
                try {
                    val response = ApiHelper.serviceApi.getMusic()
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

    fun insertSongs (songs: List<Song>) {
        viewModelScope.launch(Dispatchers.IO){
            repo.insertSongs(songs)
        }
    }
}