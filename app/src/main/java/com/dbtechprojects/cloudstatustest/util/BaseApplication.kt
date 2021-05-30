package com.dbtechprojects.cloudstatustest.util

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
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

        // only enqueue the work once, that is, when the app is first installed and opened for the first time.
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(Constants.AWS_WORK_REQUEST_UNIQUE_ID, ExistingPeriodicWorkPolicy.KEEP, awsWorkRequest)
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(Constants.GCP_WORK_REQUEST_UNIQUE_ID, ExistingPeriodicWorkPolicy.KEEP, gcpWorkRequest)
    }
}
