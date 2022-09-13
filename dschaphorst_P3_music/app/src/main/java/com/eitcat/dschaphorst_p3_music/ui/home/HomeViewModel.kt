package com.eitcat.dschaphorst_p3_music.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.eitcat.dschaphorst_p3_music.data.api.ApiHelper
import com.eitcat.dschaphorst_p3_music.data.api.ApiRepo
import com.eitcat.dschaphorst_p3_music.data.api.ApiService
import com.eitcat.dschaphorst_p3_music.data.database.MusicDB
import com.eitcat.dschaphorst_p3_music.data.database.LocalRepo
import com.eitcat.dschaphorst_p3_music.data.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text
    var songsDataSet : LiveData<List<Song>>
    private val repo: LocalRepo
    private val serviceApi: ApiHelper = ApiService.musicService

    init {
        val musicDAO = MusicDB.getDB(application).getMusicDAO()
        repo = LocalRepo(/*TODO Add ApiHelper call*/ musicDAO)
        songsDataSet = repo.localMusicData
    }

    fun pullMusicData(){
        viewModelScope.launch(Dispatchers.IO) {
            serviceApi.getClassicMusic()
        }
    }

    fun insertSongs (songs: List<Song>) {
        viewModelScope.launch(Dispatchers.IO){
            repo.insertSong(songs)
        }
    }
}