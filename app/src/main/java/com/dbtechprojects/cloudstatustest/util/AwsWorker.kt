package com.dbtechprojects.cloudstatustest.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

private const val NOTIFICATION_CHANNEL_ID = "2"
private const val NOTIFICATION_ID = 3

@HiltWorker
class AwsWorker
@AssistedInject
constructor(@Assisted context: Context, @Assisted workerParams: WorkerParameters, private val repository: MainRepository) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork() = withContext(IO) {

        val dao = repository.getCacheDatabaseDao()
        val oldList = dao.getAwsEvents()
        repository.fetchFromAwsApi()
        val newList = dao.getAwsEvents()

        if (newList != oldList) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createNotificationChannel()
            createNotification()
        }

        Result.success()
    }

    private fun createNotification() {
        val notification = NotificationCompat
            .Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_new_releases_24)
            .setContentTitle(applicationContext.getString(R.string.notification_title))
            .setContentText(applicationContext.getString(R.string.notification_text, "AWS"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(applicationContext).notify(NOTIFICATION_ID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, applicationContext.getString(R.string.notification_channel_name), importance)
        NotificationManagerCompat.from(applicationContext).createNotificationChannel(channel)
    }
}
