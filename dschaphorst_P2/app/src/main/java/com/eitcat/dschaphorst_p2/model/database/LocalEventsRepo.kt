package com.eitcat.dschaphorst_p2.model.database

import com.eitcat.dschaphorst_p2.model.EventDomain
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface LocalEventsRepo {
    fun insertEvent(events:List<EventDomain>): Completable
    fun getEvents(): Single<List<EventDomain>>
    fun findEvent(eventTitle: String): Single<EventDomain>
}

class LocalEventsRepoImpl(
    private val eventsDAO: EventsDAO,
    private val ioScheduler: Scheduler = Schedulers.io()
) : LocalEventsRepo {
    override fun insertEvent(events: List<EventDomain>): Completable =
        eventsDAO.insertEvents(events)
            .subscribeOn(ioScheduler)

    override fun getEvents(): Single<List<EventDomain>> =
        eventsDAO.getEvents()
            .subscribeOn(ioScheduler)
            .observeOn(AndroidSchedulers.mainThread())

    override fun findEvent(eventTitle: String): Single<EventDomain> =
        eventsDAO.findEvent(eventTitle)
            .subscribeOn(ioScheduler)
            .observeOn(AndroidSchedulers.mainThread())

}