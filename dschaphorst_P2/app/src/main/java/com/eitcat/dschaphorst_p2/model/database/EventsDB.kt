package com.eitcat.dschaphorst_p2.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eitcat.dschaphorst_p2.model.EventDomain
import com.eitcat.dschaphorst_p2.model.util.Converter

@Database(entities = [EventDomain::class], version = 1)
@TypeConverters(Converter::class)
abstract class EventsDB : RoomDatabase() {
    abstract fun getEventsDao(): EventsDAO

    companion object {
        @Volatile
        private var INSTANCE: EventsDB? = null
        fun getDB(context : Context) : EventsDB{
            val tempDB = INSTANCE
            if (tempDB != null) return tempDB
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventsDB::class.java, "temp-event-db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}


// val MIGRATION_X_Y = Migration(X, Y) { database -> database.execSQL( [SQL Migration Code] )