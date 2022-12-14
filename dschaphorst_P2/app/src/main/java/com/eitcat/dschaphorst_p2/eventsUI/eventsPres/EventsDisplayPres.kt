package com.eitcat.dschaphorst_p2.eventsUI.eventsPres

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.eitcat.dschaphorst_p2.model.EventDomain
import com.eitcat.dschaphorst_p2.model.database.EventsDAO
import com.eitcat.dschaphorst_p2.model.database.LocalEventsRepo
//import com.eitcat.dschaphorst_p2.model.database.LocalEventsRepoImpl
import io.reactivex.disposables.CompositeDisposable

interface EventsDisplayPres {
    fun init(viewContract: ViewContractEvents)
    fun getEvents()
    fun destroy()
}

private const val TAG = "EventsDisplayPres"

class EventsDisplayPresImpl(
//    private val eventsDAO: EventsDAO,
    private var viewContractEvents: ViewContractEvents? = null,
    private val disposables: CompositeDisposable = CompositeDisposable(),
//    private val repository: LocalEventsRepo = LocalEventsRepoImpl(eventsDAO)
): EventsDisplayPres {
    override fun init(viewContract: ViewContractEvents) {
        Log.d(TAG, "View being passed: ${viewContract}")
        viewContractEvents = viewContract
    }

    override fun getEvents() {
        viewContractEvents?.loadingEvents(true)
        Log.d("data",viewContractEvents?.loadingEvents(true).toString())
//        repository.getEvents()
//            .subscribe(
//                { events -> viewContractEvents?.onSuccess(events)},
//                { error -> viewContractEvents?.onFailure(error)}
//            )
//            .also { disposables.add(it)}
    }

    override fun destroy() {
        disposables.clear()
        viewContractEvents = null
    }


}

interface ViewContractEvents {
    fun loadingEvents(isLoading: Boolean)
    fun onSuccess(events: List<EventDomain>)
    fun onFailure(error: Throwable)
}