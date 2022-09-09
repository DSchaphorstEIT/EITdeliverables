package com.eitcat.dschaphorst_p2.eventsUI.eventsView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eitcat.dschaphorst_p2.R
import com.eitcat.dschaphorst_p2.databinding.FragmentEventsDetailsBinding
import com.eitcat.dschaphorst_p2.model.EventDomain
import com.eitcat.dschaphorst_p2.model.util.SharedEventView

private const val ARG_PARAM1 = "eventCard"

/**
 * A simple [Fragment] subclass.
 * Use the [EventsDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventsDetails : Fragment() {
    private var curEvent: EventDomain? = null

    private val binding by lazy {
        FragmentEventsDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[SharedEventView::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        curEvent = viewModel.curEvent

        binding.eventTitle.text = curEvent?.eventTitle
        binding.eventCategory.text = curEvent?.eventCategory
        ("${curEvent?.eventDate?.month ?: ""} " +
                "${curEvent?.eventDate?.dayOfMonth ?: ""}, " +
                "${curEvent?.eventDate?.year ?: ""} \n" +
                "${curEvent?.eventDate?.hour ?: ""}: " +
                "${curEvent?.eventDate?.minute ?: ""}"
                ).also { binding.eventDate.text = it }
        binding.eventDescription.text = curEvent?.eventDescription

        binding.btnModify.setOnClickListener {
            viewModel.isModify = true
            findNavController().navigate(R.id.action_eventsDetails_to_eventsEditor)
        }

        return binding.root
    }
}