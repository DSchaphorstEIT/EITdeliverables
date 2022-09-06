package com.eitcat.dschaphorst_p2.eventsUI.eventsView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.eitcat.dschaphorst_p2.databinding.FragmentEventsDisplayBinding
import com.eitcat.dschaphorst_p2.eventsUI.adapter.EventAdapter
import com.eitcat.dschaphorst_p2.model.EventData
import java.time.LocalDate

class EventsDisplay : Fragment() {

    private val binding by lazy {
        FragmentEventsDisplayBinding.inflate(layoutInflater)
    }

    private val eventAdapter by lazy {
        EventAdapter() {
            Toast.makeText(context, "Click from highorder: ${it.title}", Toast.LENGTH_LONG).show()
        }
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

        eventAdapter.updateEvent( EventData(
            "Initial Test",
            "Testing",
            LocalDate.parse("02-15-1993"),
            "This should be the first entry in the list."
        ))

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = EventsDisplay()
    }
}