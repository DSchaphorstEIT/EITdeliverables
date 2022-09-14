package com.eitcat.dschaphorst_p3_music.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.eitcat.dschaphorst_p3_music.data.api.ApiHelper
import com.eitcat.dschaphorst_p3_music.data.database.MusicDB
import com.eitcat.dschaphorst_p3_music.data.database.LocalRepo
import com.eitcat.dschaphorst_p3_music.data.model.Song
import com.eitcat.dschaphorst_p3_music.data.model.mapToSongList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class HomeViewModel(application: Application) : AndroidViewModel(application) {
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text
    var songsDataSet : LiveData<List<Song>>
    private val repo: LocalRepo

    init {
        val musicDAO = MusicDB.getDB(application).getMusicDAO()
        repo = LocalRepo(musicDAO)
        songsDataSet = repo.localMusicData
    }

    fun pullMusicData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val musicData = ApiHelper.serviceApi.getMusic().body()?.songData ?: emptyList()
                repo.insertSongs(musicData.mapToSongList())
                Log.d(TAG, "pullMusicData: ${musicData.first().artistName}")
            } catch (e : Exception){
                Log.e(TAG, "Caught Error: ${e.localizedMessage}", e)
            }
        }
    }

    fun insertSongs (songs: List<Song>) {
        viewModelScope.launch(Dispatchers.IO){
            repo.insertSongs(songs)
        }
    }
}