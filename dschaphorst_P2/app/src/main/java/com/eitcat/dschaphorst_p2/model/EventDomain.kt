package com.eitcat.dschaphorst_p2.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDateTime

@Entity(tableName = "event_table")
data class EventDomain(
    @PrimaryKey val eventTitle: String,
    val eventCategory: String,
    @TypeConverters
    val eventDate: LocalDateTime,
    val eventDescription: String
)

fun List<EventData>.mapToDomainEventList(): List<EventDomain> = this.map {
    EventDomain(
        eventTitle = it.title,
        eventCategory = it.category ?: "",
        eventDate = it.date,
        eventDescription = it.description ?: ""
    )
}

fun EventData.mapToDomainEvents(): EventDomain = EventDomain(
    eventTitle = this.title,
    eventCategory = this.category ?: "",
    eventDate = this.date,
    eventDescription = this.description ?: ""
)