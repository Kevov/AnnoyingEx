package com.example.annoyingex

import android.app.Application
import com.example.annoyingex.Manager.AnnoyingExWorkManager
import com.example.annoyingex.Manager.ApiManager
import com.example.annoyingex.Manager.ExNotificationManager

class AnnoyingExApp: Application() {
    lateinit var apiManager: ApiManager
        private set
    lateinit var annoyingExWorkManager: AnnoyingExWorkManager
        private set
    lateinit var notificationManager: ExNotificationManager
        private set
    var listOfMessages: List<String>? = null

    override fun onCreate() {
        super.onCreate()

        apiManager = ApiManager(this)
        annoyingExWorkManager = AnnoyingExWorkManager(this)
        notificationManager = ExNotificationManager(this)
    }
}