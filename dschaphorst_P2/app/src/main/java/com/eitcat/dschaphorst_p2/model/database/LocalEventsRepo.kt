package com.eitcat.dschaphorst_p2.model.database

import androidx.lifecycle.LiveData
import com.eitcat.dschaphorst_p2.model.EventDomain
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val TAG = "LocalEventsRepo"
//
//interface LocalEventsRepo {
//    fun insertEvent(events: EventDomain): Completable
//    fun getEvents(): Single<List<EventDomain>>
//    fun findEvent(eventTitle: String): Single<EventDomain>
//}
//
//class LocalEventsRepoImpl(
//    private val eventsDAO: EventsDAO,
//    private val ioScheduler: Scheduler = Schedulers.io()
//) : LocalEventsRepo {
//    override fun insertEvent(events: EventDomain): Completable =
//        eventsDAO.insertEvents(events)
//            .subscribeOn(ioScheduler)
//
//    override fun getEvents(): Single<List<EventDomain>> =
//        eventsDAO.getEvents()
//            .subscribeOn(ioScheduler)
//            .observeOn(AndroidSchedulers.mainThread())
//
//    override fun findEvent(eventTitle: String): Single<EventDomain> =
//        eventsDAO.findEvent(eventTitle)
//            .subscribeOn(ioScheduler)
//            .observeOn(AndroidSchedulers.mainThread())
//
//}

class LocalEventsRepo (private val eventsDAO: EventsDAO) {
    val readAllEvents : LiveData<List<EventDomain>> = eventsDAO.getEvents()

    suspend fun insertEvent (event: EventDomain) {
        eventsDAO.insertEvents(event)
    }
}