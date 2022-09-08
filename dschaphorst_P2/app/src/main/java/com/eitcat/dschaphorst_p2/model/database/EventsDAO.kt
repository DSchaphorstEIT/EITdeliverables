package com.eitcat.dschaphorst_p2.model.database

import androidx.room.*
import com.eitcat.dschaphorst_p2.model.EventDomain
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface EventsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(events: List<EventDomain>): Completable

    @Query("SELECT * from eventdomain")
    fun getEvents(): Single<List<EventDomain>>

    @Query("SELECT * from eventdomain WHERE eventtitle LIKE :title")
    fun findEvent(title: String): Single<EventDomain>

    @Delete
    fun deleteEvent(eventDomain: EventDomain): Completable
}