package com.eitcat.dschaphorst_p2.eventsUI.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eitcat.dschaphorst_p2.databinding.EventItemBinding
import com.eitcat.dschaphorst_p2.model.EventData
import com.eitcat.dschaphorst_p2.model.EventDomain

class EventAdapter(
    private val eventDataSet: MutableList<EventDomain> = mutableListOf(),
    private val onEventClickHandler: (EventDomain) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {

    fun updateEvent(newEvent: EventDomain){

        eventDataSet.add(newEvent).also {
            if (it) {
                eventDataSet.sortBy { data -> data.eventDate }
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
    fun bind(event: EventDomain, onEventClickHandler: (EventDomain) -> Unit){
        binding.eventTitle.text = event.eventTitle
        binding.eventDate.text = event.eventDate.toString()

        binding.root.setOnClickListener{
            onEventClickHandler.invoke(event)
        }
    }
}