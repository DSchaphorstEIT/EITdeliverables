package com.eitcat.dschaphorst_p2.eventsUI.eventsView

import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
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
import com.eitcat.dschaphorst_p2.model.util.*
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

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

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[SharedEventView::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotifChannel()
        binding.btnNotif.setOnClickListener { scheduleNotif()}
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

    private fun scheduleNotif() {
        val intent = Intent(requireActivity().applicationContext, Notifications::class.java)
        val title = viewModel.curEvent?.eventTitle ?: "No Title for Event"
        val msg = viewModel.curEvent?.eventDescription ?: "No Description for event."
        intent.putExtra(notifTitle, title)
        intent.putExtra(notifMessage, msg)

        val pendingIntent = PendingIntent.getBroadcast(
            requireActivity().applicationContext,
            notifID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val timeInMills = viewModel.curEvent?.eventDate?.toInstant(ZoneOffset.UTC)?.toEpochMilli() ?: LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeInMills,
            pendingIntent
        )
        showAlert(title, msg)
    }

    private fun showAlert(title: String, msg: String) {
        val date = curEvent?.eventDate ?: LocalDateTime.now()
        val dateStr = ("${date.month} " +
                "${date.dayOfMonth}, " +
                "${date.year} - " +
                (if (date.hour > 12) "${date.hour -12}:" else "${date.hour}") +
                (if (date.minute < 10) "0" else "") +
                "${date.minute}"
                )
        AlertDialog.Builder(requireActivity())
            .setTitle("Notification Scheduled")
            .setMessage("Title: $title \nMessage: $msg \nAt: $dateStr")
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }

    private fun createNotifChannel() {
        val name = "Notif Channel"
        val desc = "A description of the channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(notifChannel, name, importance)
        channel.description = desc
        val notificationManager = requireActivity().getSystemService(NOTIFICATION_SERVICE)  as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}