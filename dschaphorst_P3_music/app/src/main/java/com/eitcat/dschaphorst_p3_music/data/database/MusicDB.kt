package com.eitcat.dschaphorst_p3_music.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eitcat.dschaphorst_p3_music.data.model.Song

@Database(entities = [Song::class], version = 1)
abstract class MusicDB : RoomDatabase(){
    abstract fun getMusicDAO(): MusicDAO

    companion object {
        @Volatile
        private var INSTANCE: MusicDB? = null
        fun getDB(context: Context) : MusicDB{
            val tempDB = INSTANCE
            if (tempDB != null) return tempDB
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MusicDB::class.java, "music_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}