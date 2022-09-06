package com.eitcat.dschaphorst_p2.eventsUI.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eitcat.dschaphorst_p2.databinding.EventItemBinding
import com.eitcat.dschaphorst_p2.model.EventData

class EventAdapter(
    private val eventDataSet: MutableList<EventData> = mutableListOf(),
    private val onEventClickHandler: (EventData) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {

    fun updateEvent(newEvent: EventData){
        eventDataSet.add(newEvent).also {
            if (it) {
                eventDataSet.sortBy { data -> data.date }
            }
        }
        notifyItemInserted(eventDataSet.indexOf(newEvent))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventDataSet[position], onEventClickHandler)
    }

    override fun getItemCount(): Int = eventDataSet.size
}

class EventViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(event: EventData, onEventClickHandler: (EventData) -> Unit){
        binding.eventTitle.text = event.title
        binding.eventCategory.text = event.category ?: "Msc."
        binding.eventDate.text = event.date.toString()
        binding.eventDescription.text = event.description ?: "No Description"

        binding.root.setOnClickListener{
            onEventClickHandler.invoke(event)
        }
    }
}