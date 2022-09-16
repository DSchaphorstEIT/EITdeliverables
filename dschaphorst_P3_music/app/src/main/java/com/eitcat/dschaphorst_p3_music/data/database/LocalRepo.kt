package com.eitcat.dschaphorst_p3_music.data.database

import androidx.lifecycle.LiveData
import com.eitcat.dschaphorst_p3_music.data.api.ApiHelper
import com.eitcat.dschaphorst_p3_music.data.model.Song
import io.reactivex.Single

class LocalRepo(
    private val musicDAO: MusicDAO
    ) {
    var localMusicData : LiveData<List<Song>> = getMusicData()

    fun getMusicData() : LiveData<List<Song>> {
        return musicDAO.getMusic()
    }
    fun insertSongs (songs: List<Song>?) {
        if (songs != null) musicDAO.insertSongs(songs)
    }
}