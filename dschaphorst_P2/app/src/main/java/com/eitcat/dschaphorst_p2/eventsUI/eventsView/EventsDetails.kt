package com.eitcat.dschaphorst_p2.eventsUI.eventsView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.eitcat.dschaphorst_p2.R
import com.eitcat.dschaphorst_p2.databinding.FragmentEventsDetailsBinding
import com.eitcat.dschaphorst_p2.model.EventDomain
import com.eitcat.dschaphorst_p2.model.util.SharedEventView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "eventCard"

/**
 * A simple [Fragment] subclass.
 * Use the [EventsDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventsDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var curEvent: EventDomain? = null

    private val binding by lazy {
        FragmentEventsDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        curEvent = ViewModelProvider(requireActivity())[SharedEventView::class.java].curEvent

        binding.eventTitle.text = curEvent?.eventTitle
        binding.eventCategory.text = curEvent?.eventCategory
        binding.eventDate.text = curEvent?.eventDate.toString()
        binding.eventDescription.text = curEvent?.eventDescription


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment EventsDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            EventsDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}