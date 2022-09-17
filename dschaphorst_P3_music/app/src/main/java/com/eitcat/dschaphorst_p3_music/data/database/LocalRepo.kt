package com.eitcat.dschaphorst_p3_music.data.database

import androidx.lifecycle.LiveData
import com.eitcat.dschaphorst_p3_music.data.api.ApiHelper
import com.eitcat.dschaphorst_p3_music.data.model.Song
import io.reactivex.Single

/**
 * Repository class used to access the local RoomDB
 *
 * @property musicDAO The Data Access Object used to interact with the database.
 */
class LocalRepo(
    private val musicDAO: MusicDAO
    ) {
    var localMusicData : LiveData<List<Song>> = getMusicData()

    /**
     * Get the music data stored in the database.
     *
     * @return [LiveData] of the [List] of [Song]
     */
    fun getMusicData() : LiveData<List<Song>> {
        return musicDAO.getMusic()
    }

    /**
     * Add songs from a list to the local database
     *
     * @param songs [List] of [Song] that will be added to the local database
     */
    fun insertSongs (songs: List<Song>?) {
        if (songs != null) musicDAO.insertSongs(songs)
    }
}