package com.eitcat.dschaphorst_p2.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eitcat.dschaphorst_p2.model.EventDomain
import io.reactivex.Completable

@Dao
interface EventsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(events: EventDomain): Completable

    @Query("SELECT * from eventdomain")
    fun getEvents(): LiveData<List<EventDomain>>

    @Query("SELECT * from eventdomain WHERE eventtitle LIKE :title")
    fun findEvent(title: String): LiveData<EventDomain>

    @Delete
    fun deleteEvent(eventDomain: EventDomain): Completable
}