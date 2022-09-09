package com.eitcat.dschaphorst_p2.eventsUI.eventsView

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.eitcat.dschaphorst_p2.R
import com.eitcat.dschaphorst_p2.databinding.FragmentEventsEditorBinding
import com.eitcat.dschaphorst_p2.eventsUI.eventsPres.*
import com.eitcat.dschaphorst_p2.model.EventDomain
import com.eitcat.dschaphorst_p2.model.database.EventsDAO
import com.eitcat.dschaphorst_p2.model.database.EventsDB
import com.eitcat.dschaphorst_p2.model.database.LocalEventsRepo
import com.eitcat.dschaphorst_p2.model.util.SharedEventView
import java.time.LocalDateTime

private const val TAG = "EventsEditor"

/**
 * A simple [Fragment] subclass.
 * Use the [EventsEditor.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventsEditor : Fragment()/*, ViewContractEditor */{

    private val binding by lazy {
        FragmentEventsEditorBinding.inflate(layoutInflater)
    }
//
//    private val presenter: EventsEditorPres by lazy {
//        EventsEditorPresImpl(eventsDAO = eventsDB.getEventsDao())
//    }
//
//    private val eventsDB by lazy {
//        Room.databaseBuilder(requireActivity().applicationContext, EventsDB::class.java, "event-db")
//            .build()
//    }

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[SharedEventView::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        presenter.init(this)
//        presenter.insertEvent(
//            EventDomain(
//                "Hardcode Test Example",
//                "Hardtest",
//                LocalDateTime.now(),
//                "This is a hardcoded DB item test and should only exist once, but constantly rewritten"
//            )
//        )
        viewModel.insertEvent(
            EventDomain(
                "Hardcode Test Example",
                "Hardtest",
                LocalDateTime.now(),
                "This is a hardcoded DB item test and should only exist once, but constantly rewritten"
            )
        )
    }

    override fun onResume() {
        super.onResume()

        binding.btnCreateEditor.setOnClickListener{
//            preseter.insertEvent(
//                EventDomain(
//                    binding.ietTitleEditor.text.toString(),
//                    binding.ietCategoryEditor.text.toString(),
//                    LocalDateTime.of(
//                        binding.dateEditor.year,
//                        binding.dateEditor.month,
//                        binding.dateEditor.dayOfMonth,
//                        binding.timeEditor.hour,
//                        binding.timeEditor.minute
//                    ),
//                    binding.ietDescriptionEditor.text.toString()
//                )
//            )

            viewModel.insertEvent(
                EventDomain(
                    binding.ietTitleEditor.text.toString(),
                    binding.ietCategoryEditor.text.toString(),
                    LocalDateTime.of(
                        binding.dateEditor.year,
                        binding.dateEditor.month,
                        binding.dateEditor.dayOfMonth,
                        binding.timeEditor.hour,
                        binding.timeEditor.minute
                    ),
                    binding.ietDescriptionEditor.text.toString()
                )
            )

            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        presenter.getEvents()

        return binding.root
    }
//
//    override fun loadingEvents(isLoading: Boolean) {
//        Toast.makeText(requireContext(), "Data Loading: $isLoading", Toast.LENGTH_LONG).show()
//        Log.d(TAG, "loading $isLoading")
//    }
//
//    override fun onSuccess(events: List<EventDomain>) {
//        Toast.makeText(requireContext(), "Success: ${events.first().eventTitle}", Toast.LENGTH_LONG).show()
//        Log.d(TAG, "onSuccess: $events")
//    }
//
//    override fun onFailure(error: Throwable) {
//        Toast.makeText(requireContext(), "FAILURE: ${error.localizedMessage}", Toast.LENGTH_LONG).show()
//        Log.d(TAG, "FAILURE ${error.localizedMessage}")
//    }
}