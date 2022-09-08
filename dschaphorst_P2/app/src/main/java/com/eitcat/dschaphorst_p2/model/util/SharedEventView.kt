package com.eitcat.dschaphorst_p2.model.util

import androidx.lifecycle.ViewModel
import com.eitcat.dschaphorst_p2.model.EventDomain

class SharedEventView : ViewModel() {
    var curEvent : EventDomain? = null

    fun sendEvent (event: EventDomain){
        curEvent = EventDomain(
            event.eventTitle,
            event.eventCategory,
            event.eventDate,
            event.eventDescription
        )
    }
}