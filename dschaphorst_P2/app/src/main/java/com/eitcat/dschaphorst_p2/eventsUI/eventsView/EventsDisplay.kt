package com.eitcat.dschaphorst_p2.eventsUI.eventsView

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.eitcat.dschaphorst_p2.R
import com.eitcat.dschaphorst_p2.databinding.FragmentEventsDisplayBinding
import com.eitcat.dschaphorst_p2.eventsUI.adapter.EventAdapter
import com.eitcat.dschaphorst_p2.eventsUI.eventsPres.EventsDisplayPres
import com.eitcat.dschaphorst_p2.eventsUI.eventsPres.EventsDisplayPresImpl
import com.eitcat.dschaphorst_p2.eventsUI.eventsPres.ViewContractEvents
import com.eitcat.dschaphorst_p2.model.EventDomain
import com.eitcat.dschaphorst_p2.model.database.EventsDB
import com.eitcat.dschaphorst_p2.model.util.SharedEventView
import java.time.LocalDateTime

private const val TAG = "EventsDisplay"

class EventsDisplay : Fragment(), ViewContractEvents {

    private val binding by lazy {
        FragmentEventsDisplayBinding.inflate(layoutInflater)
    }

    private val presenter: EventsDisplayPres by lazy {
        EventsDisplayPresImpl(eventsDAO = eventsDB.getEventsDao())
    }

    private val eventAdapter by lazy {
        EventAdapter() {
            ViewModelProvider(requireActivity())[SharedEventView::class.java].sendEvent(it)
            binding.root.findNavController().navigate(R.id.action_eventsDisplay_to_eventsDetails)
            Toast.makeText(context, "Click from highorder: ${it.eventTitle}", Toast.LENGTH_LONG).show()
        }
    }

    private val eventsDB by lazy {
        Room.databaseBuilder(requireActivity().applicationContext, EventsDB::class.java, "event-db")
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.detailsRecycleView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = eventAdapter
        }

        eventAdapter.updateEvent( EventDomain(
            "Initial Test",
            "Testing",
            LocalDateTime.now(),
            "This should be the first entry in the list."
        ))

        presenter.getEvents()

        return binding.root
    }

    override fun loadingEvents(isLoading: Boolean) {
        Toast.makeText(requireContext(), "Data Loading: $isLoading", Toast.LENGTH_LONG).show()
        Log.d(TAG, "loading $isLoading")
    }

    override fun onSuccess(events: List<EventDomain>) {
        Toast.makeText(requireContext(), "Success: ${events.first().eventTitle}", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onSuccess: $events")
        events.forEach{ event ->
            eventAdapter.updateEvent(event)
        }
    }

    override fun onFailure(error: Throwable) {
        Toast.makeText(requireContext(), "FAILURE: ${error.localizedMessage}", Toast.LENGTH_LONG).show()
        Log.d(TAG, "FAILURE ${error.localizedMessage}")
    }
}