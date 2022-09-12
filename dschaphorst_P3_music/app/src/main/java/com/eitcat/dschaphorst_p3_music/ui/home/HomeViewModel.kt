package com.eitcat.dschaphorst_p3_music.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.eitcat.dschaphorst_p3_music.data.database.MusicDB
import com.eitcat.dschaphorst_p3_music.data.database.Repo
import com.eitcat.dschaphorst_p3_music.data.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text
    var songsDataSet : LiveData<List<Song>>
    private val repo: Repo

    init {
        val musicDAO = MusicDB.getDB(application).getMusicDAO()
        repo = Repo(/*TODO Add ApiHelper call*/ musicDAO)
        songsDataSet = repo.localMusicData
    }

    fun insertSong (song: Song) {
        viewModelScope.launch(Dispatchers.IO){
            repo.insertSong(song)
        }
    }
}