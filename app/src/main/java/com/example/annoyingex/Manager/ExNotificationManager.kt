package com.example.annoyingex.Manager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.annoyingex.AnnoyingExApp
import com.example.annoyingex.MainActivity
import com.example.annoyingex.R
import kotlin.random.Random

class ExNotificationManager(private val context: Context) {
    private val notificationManager: NotificationManager =
        getSystemService(context, NotificationManager::class.java) as NotificationManager

    companion object {
        const val MSG_CHANNEL_ID = "MSG_CHANNEL_ID"
    }

    init {
        createNotificationChannel()
    }

    fun msgNotification() {
        val listOfMessages = (context as AnnoyingExApp).listOfMessages
        val appIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingAppIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        listOfMessages?.let {
            val randPos = Random.nextInt(it.size)
            val notification = buildNotification(it[randPos], pendingAppIntent)
            notificationManager.notify(randPos, notification)
        } ?: run{
            val notification = buildNotification("unable to retrieve message", pendingAppIntent)
            notificationManager.notify(-1, notification)
        }
    }

    private fun buildNotification(content: String, intent: PendingIntent): Notification {
        return NotificationCompat.Builder(context, MSG_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setCategory("message")
            .setContentTitle("Annoying Ex ughh")
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(intent)
            .setAutoCancel(true)
            .build()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Annoying Ex ughhh"
            val descriptionText = "Haha phone goes brrr"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(MSG_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
    }
}