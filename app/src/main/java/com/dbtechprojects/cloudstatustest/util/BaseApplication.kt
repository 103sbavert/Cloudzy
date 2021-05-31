package com.dbtechprojects.cloudstatustest.util

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.dbtechprojects.cloudstatustest.R
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    private val awsWorkRequest = PeriodicWorkRequestBuilder<AwsWorker>(1, TimeUnit.HOURS).build()
    private val gcpWorkRequest = PeriodicWorkRequestBuilder<GcpWorker>(1, TimeUnit.HOURS).build()

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        // create notification channels
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createNotificationChannel()

        // only enqueue the work once, that is, when the app is first installed and opened for the first time.
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(Constants.AWS_WORK_REQUEST_UNIQUE_ID, ExistingPeriodicWorkPolicy.KEEP, awsWorkRequest)
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(Constants.GCP_WORK_REQUEST_UNIQUE_ID, ExistingPeriodicWorkPolicy.KEEP, gcpWorkRequest)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val awsNotificationChannel = NotificationChannel(
            Constants.AWS_NOTIFICATION_CHANNEL_ID,
            getString(R.string.notification_channel_name, "AWS"),
            importance
        )
        val gcpNotificationChannel = NotificationChannel(
            Constants.GCP_NOTIFICATION_CHANNEL_ID,
            getString(R.string.notification_channel_name, "GCP"),
            importance
        )
        NotificationManagerCompat.from(this).createNotificationChannel(awsNotificationChannel)
        NotificationManagerCompat.from(this).createNotificationChannel(gcpNotificationChannel)
    }
}
