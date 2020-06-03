package com.example.annoyingex.Model

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.annoyingex.AnnoyingExApp

class MessageSpamWork (private val context: Context, workParams: WorkerParameters): Worker(context , workParams) {

    override fun doWork(): Result {
        Log.i("lol", "Do work")
        (context as AnnoyingExApp).notificationManager.msgNotification()
        return Result.success()
    }
}