package com.eitcat.dschaphorst_p2.model.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.eitcat.dschaphorst_p2.model.EventDomain
import com.eitcat.dschaphorst_p2.model.database.EventsDB
import com.eitcat.dschaphorst_p2.model.database.LocalEventsRepo
import okhttp3.Dispatcher

class SharedEventView(application: Application) : AndroidViewModel(application) {
    var curEvent : EventDomain? = null
    var allEvents : LiveData<List<EventDomain>>
    private val repo: LocalEventsRepo

    init {
        val eventDAO = EventsDB.getDB(application).getEventsDao()
        repo = LocalEventsRepo(eventDAO)
        allEvents = repo.readAllEvents
    }

    fun sendEvent (event: EventDomain){
        curEvent = EventDomain(
            event.eventTitle,
            event.eventCategory,
            event.eventDate,
            event.eventDescription
        )
    }

    fun insertEvent (event: EventDomain){
//        viewModelScope.Launch(Dispatcher.IO) { TODO Why is .Launch not allowed?
//            repo.insertEvent(event)
//        }
        repo.insertEvent(event)
    }
}