package com.dbtechprojects.cloudzy.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dbtechprojects.cloudzy.R
import com.dbtechprojects.cloudzy.repository.MainRepository
import com.dbtechprojects.cloudzy.ui.main.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

private const val NOTIFICATION_ID = 3

@HiltWorker
class GcpWorker
@AssistedInject
constructor(@Assisted context: Context, @Assisted workerParams: WorkerParameters, private val repository: MainRepository) :
    CoroutineWorker(context, workerParams) {

    private val preferences = (applicationContext as BaseApplication).preferences

    override suspend fun doWork() = withContext(IO) {

        val shouldShowNotification = preferences.getBoolean(applicationContext.getString(R.string.gcp_notif_pref_key), true)

        if (shouldShowNotification && repository.updateGcpDb()) {
            createAndPushNotification()
        }

        Result.success()
    }

    private fun createAndPushNotification() {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            action = Constants.GCP_INTENT_ACTION
        }
        val pendingIntent: PendingIntent? = TaskStackBuilder.create(applicationContext).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(Constants.PENDING_INTENT_REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat
            .Builder(applicationContext, Constants.GCP_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(applicationContext.getString(R.string.notification_title, "GCP"))
            .setContentText(applicationContext.getString(R.string.notification_text, "GCP"))
            .setStyle(NotificationCompat.BigTextStyle())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()
        NotificationManagerCompat.from(applicationContext).notify(NOTIFICATION_ID, notification)
    }
}
