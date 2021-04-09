package com.udacity

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

private val CHANNEL_ID = 0
private val CHANNEL = "Downloads"

// This is the code needed to create and trigger a notification
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

//    if(!isNotificationChannelAvailable(this)) {
//        10
//        createNotificationChannel(this)
//        11
//    }

    // TODO: Step 1.2 get an instance of NotificationCompat.Builder
    val builder = NotificationCompat.Builder(
            applicationContext,
            applicationContext.getString(R.string.notification_id)
    )

            // TODO: Step 1.3 set title, text and icon to builder
            .setSmallIcon(R.drawable.circle_icons_download)
            .setContentTitle(applicationContext
                    .getString(R.string.notification_title))
            .setContentText(messageBody)

    // TODO: Step 1.4 call notify to send the notification
    // Deliver the notification
    notify(CHANNEL_ID, builder.build())
}