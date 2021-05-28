package com.dbtechprojects.cloudstatustest.util

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

//class Worker(val context: Context, workerParams: WorkerParameters, private val repository: MainRepository) : CoroutineWorker(context, workerParams) {
//    override suspend fun doWork(): Result {
//        withContext(IO) {
//            val dao = repository.getDatabaseDao()
//            val oldList = dao.getAwsEvents()
//            repository.fetchFromAwsApi(this)
//            val newList = dao.getAwsEvents()
//
//            if (newList != oldList) pushNotification()
//        }
//        return Result.success()
//    }
//}
