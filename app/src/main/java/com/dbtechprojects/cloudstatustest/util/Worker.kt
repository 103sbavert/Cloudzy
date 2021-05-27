package com.dbtechprojects.cloudstatustest.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO

class ApiFetchWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val scope = CoroutineScope(IO)

        return Result.success()
    }
}
