package com.eitcat.dschaphorst_p2.model.util

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.eitcat.dschaphorst_p2.R

const val notifID = 1
const val notifChannel = "channel1"
const val notifTitle = "Title Extra"
const val notifMessage = "Message Extra"

class Notifications : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notification = NotificationCompat.Builder(context, notifChannel)
            .setSmallIcon(R.drawable.ic_baseline_info_24)
            .setContentTitle(intent?.getStringExtra(notifTitle))
            .setContentText(intent?.getStringExtra(notifMessage))
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notifID, notification)
    }
}