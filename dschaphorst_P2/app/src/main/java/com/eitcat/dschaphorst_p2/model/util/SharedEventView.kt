package com.eitcat.dschaphorst_p2.model.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.eitcat.dschaphorst_p2.model.EventDomain
import com.eitcat.dschaphorst_p2.model.database.EventsDB
import com.eitcat.dschaphorst_p2.model.database.LocalEventsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedEventView(application: Application) : AndroidViewModel(application) {
    var isModify : Boolean = false
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
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertEvent(event)
        }
    }
}