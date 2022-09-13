package com.eitcat.dschaphorst_p3_music.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.eitcat.dschaphorst_p3_music.data.database.LocalRepo
import com.eitcat.dschaphorst_p3_music.data.model.Song
import com.eitcat.dschaphorst_p3_music.data.model.mapToSong
import com.eitcat.dschaphorst_p3_music.data.model.mapToSongList
import javax.inject.Inject

private const val TAG = "ApiRepo"

interface ApiRepo {
    suspend fun getAllMusic(): LiveData<List<Song>>
    fun searchMusic(songName: String): LiveData<Song>
}

class ApiRepoImpl @Inject constructor(
    private val musicApi: ApiHelper,
    private val localRepo: LocalRepo
) : ApiRepo {

    override suspend fun getAllMusic(): LiveData<List<Song>> =
        musicApi.getClassicMusic()
            .map {
                Log.d(TAG, "Music import list: $it")
                localRepo.insertSong(it.mapToSongList())
                localRepo.localMusicData.value ?: emptyList()
            }

    override fun searchMusic(songName: String): LiveData<Song> =
        musicApi.searchMusic(songName)
            .map { it.mapToSong() }

}