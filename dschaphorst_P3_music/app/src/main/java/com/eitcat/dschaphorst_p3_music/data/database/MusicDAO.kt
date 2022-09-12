package com.eitcat.dschaphorst_p3_music.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eitcat.dschaphorst_p3_music.data.model.Song
import io.reactivex.Completable

@Dao
interface MusicDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Query("SELECT * from song_list")
    fun getMusic(): LiveData<List<Song>>

    @Delete
    fun deleteSong(song: Song): Completable
}