package com.example.dschaphorst_modiscc_calendar.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dschaphorst_modiscc_calendar.R
import com.example.dschaphorst_modiscc_calendar.databinding.FragmentAvailabilityBinding
import com.example.dschaphorst_modiscc_calendar.util.OrderTypes
import com.example.dschaphorst_modiscc_calendar.view.adapter.AvailabilityAdapter

class AvailabilityFragment : Fragment() {

    private var _binding: FragmentAvailabilityBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[AvailabilityViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAvailabilityBinding.inflate(inflater, container, false)

        /**
         * Creating the Adapter for the Recycler View
         */
        val availabilityAdapter = AvailabilityAdapter()
        binding.availabilityRecycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = availabilityAdapter
        }

        /**
         * Creating the Adapter for the Spinner to select order type, and set the
         * onItemSelectedListener to modify the Recycler View depending on the selected order type.
         * Only allowing inputs of [OrderTypes]
         */
        binding.orderTypeSelector.apply {
            adapter = ArrayAdapter(
                requireActivity(), android.R.layout.simple_spinner_item, OrderTypes.values()
            )
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.findAvailableOrderingTimes(
                        d = binding.orderTypeSelector.selectedItem.toString().lowercase()
                    )
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    viewModel.findAvailableOrderingTimes()
                }
            }
        }

        /**
         * Observe the data changes to populate the views.
         */
        viewModel.input.observe(viewLifecycleOwner) {
            availabilityAdapter.setDates(it)
        }

        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}