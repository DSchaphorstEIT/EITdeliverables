package com.eitcat.dschaphorst_p2.eventsUI.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eitcat.dschaphorst_p2.databinding.EventItemBinding
import com.eitcat.dschaphorst_p2.model.EventDomain

class EventAdapter(
    private var eventDataSet: List<EventDomain> = emptyList(),
    private val onEventClickHandler: (EventDomain) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {
//
//    fun updateEvents(newEvents: List<EventDomain>){
//
//        eventDataSet.add(newEvent).also {
//            if (it) {
//                eventDataSet.sortBy { data -> data.eventDate }
//            }
//        }
//        notifyItemInserted(eventDataSet.indexOf(newEvent))
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventDataSet[position], onEventClickHandler)
    }

    override fun getItemCount(): Int = eventDataSet.size

    fun setData(events: List<EventDomain>){
        eventDataSet = events
        notifyDataSetChanged() // This needs a better way to do it but is required for now.
    }
}

class EventViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(event: EventDomain, onEventClickHandler: (EventDomain) -> Unit){
        binding.eventTitle.text = event.eventTitle
        ("${event.eventDate.month} " +
                "${event.eventDate.dayOfMonth}, " +
                "${event.eventDate.year} \n" +
                (if(event.eventDate.hour > 12) "${event.eventDate.hour-12}:" else "${event.eventDate.hour}:") +
                (if(event.eventDate.minute < 10) "0" else "") +
                "${event.eventDate.minute}"
                ).also { binding.eventDate.text = it }

        binding.root.setOnClickListener{
            onEventClickHandler.invoke(event)
        }
    }
}