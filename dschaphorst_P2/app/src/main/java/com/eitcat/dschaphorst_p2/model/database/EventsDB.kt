package com.eitcat.dschaphorst_p2.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eitcat.dschaphorst_p2.model.EventDomain
import com.eitcat.dschaphorst_p2.model.util.Converter

@Database(entities = [EventDomain::class], version = 1)
@TypeConverters(Converter::class)
abstract class EventsDB : RoomDatabase() {
    abstract fun getEventsDao(): EventsDAO
}

// val MIGRATION_X_Y = Migration(X, Y) { database -> database.execSQL( [SQL Migration Code] )