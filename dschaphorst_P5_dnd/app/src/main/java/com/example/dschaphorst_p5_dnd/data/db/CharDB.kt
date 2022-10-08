package com.example.dschaphorst_p5_dnd.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dschaphorst_p5_dnd.data.model.domain.Character5e

/**
 * The [RoomDatabase] that will be used for local storage.
 *
 */
//@Database(entities = [Character5e::class], version = 1)
//abstract class CharDB : RoomDatabase(){
//    abstract fun getCharDAO(): CharDAO
//
//    /**
//     * If database does not already exist, create it.
//     */
//    companion object {
//        @Volatile
//        private var INSTANCE: CharDB? = null
//        fun getDB(context: Context) : CharDB {
//            val tempDB = INSTANCE
//            if (tempDB != null) return tempDB
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    CharDB::class.java, "character_db"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}