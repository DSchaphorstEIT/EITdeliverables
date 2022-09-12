package com.eitcat.dschaphorst_p3_music.data.database

import androidx.lifecycle.LiveData
import com.eitcat.dschaphorst_p3_music.data.api.ApiHelper
import com.eitcat.dschaphorst_p3_music.data.model.Song
import io.reactivex.Single

class Repo(
//    private val apiHelper: ApiHelper,
    private val musicDAO: MusicDAO
    ) {
//
//    fun getMusic(): Single<List<Song>> {
//        return apiHelper.getMusic()
//    }
    val localMusicData : LiveData<List<Song>> = musicDAO.getMusic()

    suspend fun insertSong (song: Song) {
        musicDAO.insertSong(song)
    }
}