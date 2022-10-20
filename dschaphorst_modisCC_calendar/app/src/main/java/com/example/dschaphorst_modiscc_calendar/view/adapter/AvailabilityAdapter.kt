package com.example.dschaphorst_modiscc_calendar.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dschaphorst_modiscc_calendar.databinding.DateCardBinding

/**
 * The [RecyclerView.Adapter] that will be used to display the available order input dates and times.
 * Expecting the logic to be handled outside of this class, and only given a List of Strings
 */
class AvailabilityAdapter(
    private var datesStrings: MutableList<String> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /**
     * Create the [AvailabilityViewHolder] that will be used by the [RecyclerView]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        AvailabilityViewHolder(
            DateCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    /**
     * Enable [holder] to be any type of [RecyclerView.ViewHolder].
     * Using [AvailabilityViewHolder] for this case.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AvailabilityViewHolder).bind(datesStrings[position])
    }

    override fun getItemCount(): Int = datesStrings.size

    /**
     * Set the list of data to be used for the [RecyclerView]
     */
    fun setDates(dates: List<String>) {
        datesStrings.clear()
        dates.forEach {
            datesStrings.add(it)
        }
        notifyDataSetChanged()
    }
}

/**
 * Bind the data to the card view.
 */
class AvailabilityViewHolder(private val binding: DateCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(dateText: String) {
        binding.dateText.text = dateText
    }
}