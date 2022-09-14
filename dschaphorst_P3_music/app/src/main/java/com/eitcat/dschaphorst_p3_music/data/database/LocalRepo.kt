package com.eitcat.dschaphorst_p3_music.data.database

import androidx.lifecycle.LiveData
import com.eitcat.dschaphorst_p3_music.data.api.ApiHelper
import com.eitcat.dschaphorst_p3_music.data.model.Song
import io.reactivex.Single

class LocalRepo(
    private val musicDAO: MusicDAO
    ) {
    val localMusicData : LiveData<List<Song>> = musicDAO.getMusic()

    fun insertSongs (songs: List<Song>?) {
        if (songs != null) musicDAO.insertSongs(songs)
    }
}