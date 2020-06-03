package com.example.annoyingex.Manager

import android.content.Context
import androidx.work.*
import com.example.annoyingex.Model.MessageSpamWork
import java.util.concurrent.TimeUnit

class AnnoyingExWorkManager(context: Context) {
    private var workManager = WorkManager.getInstance(context)

    companion object {
        const val MESSAGE_SPAM_TAG = "MESSAGE_SPAM_TAG"
    }

    fun startMessageSpamming() {
        if (!isWorkRunning()) {
            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<MessageSpamWork>(20, TimeUnit.MINUTES)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .addTag(MESSAGE_SPAM_TAG)
                .build()


            workManager.enqueue(workRequest)
        }
    }

    private fun isWorkRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(MESSAGE_SPAM_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun blockMsg() {
        workManager.cancelAllWorkByTag(MESSAGE_SPAM_TAG)
    }
}