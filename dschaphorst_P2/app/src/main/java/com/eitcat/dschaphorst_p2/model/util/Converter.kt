package com.eitcat.dschaphorst_p2.model.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset

class Converter {
    @TypeConverter
    fun fromTimestamp(timestampInMillis: Long): LocalDateTime? {
        return LocalDateTime.ofEpochSecond(timestampInMillis, 0, ZoneOffset.UTC)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(ZoneOffset.UTC)
    }
}