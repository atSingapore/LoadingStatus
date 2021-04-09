package com.udacity

import android.app.DownloadManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private var downloadID: Long = 0
    var selectedURL: String? = null

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        custom_button.setOnClickListener {
            download()
        }

        // EggTimerFragment.kt
        // TODO: Step 1.7 call createChannel
        createChannel(
                getString(R.string.notification_id),
                getString(R.string.notification_title)
        )

    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)

            if(downloadID == id) {

                // Reset download state
                custom_button.buttonState = ButtonState.Completed

            }
        }
    }

    private fun download() {
        // If a radio button has been selected
        if(selectedURL != null)
        {
            custom_button.buttonState = ButtonState.Loading

            Log.i("MainViewModel", "downloading $selectedURL")
            val request =
                    DownloadManager.Request(Uri.parse(selectedURL)) // previously was URL
                            .setTitle(getString(R.string.app_name))
                            .setDescription(getString(R.string.app_description))
                            .setRequiresCharging(false)
                            .setAllowedOverMetered(true)
                            .setAllowedOverRoaming(true)


            val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            downloadID =
                    downloadManager.enqueue(request)// enqueue puts the download request in the queue.

            //val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager

            notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
            if(this::notificationManager.isInitialized) {
                notificationManager.sendNotification(applicationContext.getString(R.string.notification_description), applicationContext)
            }

        } else
        {
            // If a radio button has not been selected
            Log.i("MainActivity", "File was not selected")

            // Set loading state to completed
            custom_button.buttonState = ButtonState.Completed

            //TODO - show a toast to remind user to select a file
            Toast.makeText(this, "Please select a file to download", Toast.LENGTH_SHORT).show()

        }
    }

    private fun createChannel(channelId: String, channelName: String) {
        // TODO: Step 1.6 START create a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    channelId,
                    channelName,
                    // TODO: Step 2.4 change importance
                    NotificationManager.IMPORTANCE_HIGH
            )
            // TODO: Step 2.6 disable badges for this channel

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Download complete"
            val notificationManager = getSystemService(
                    NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)


            // TODO: Step 1.6 END create channel


        }

        val builder = NotificationCompat.Builder(
                applicationContext,
                // TODO: Step 1.8 verify the notification channel name
                applicationContext.getString(R.string.notification_id)
        )
    }



    fun onGlideSelected(view: View)
    {
        Log.i("MainActivity", "onGlideSelected")
        selectedURL = "https://github.com/bumptech/glide/archive/refs/heads/master.zip"
    }

    fun onLoadAppSelected(view: View)
    {
        Log.i("MainActivity", "onLoadAppSelected")
        selectedURL = "https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/refs/heads/master.zip"
    }

    fun onRetrofitSelected(view: View)
    {
        Log.i("MainActivity", "onRetrofitSelected")
        selectedURL = "https://github.com/square/retrofit/archive/refs/heads/master.zip"
    }
}


