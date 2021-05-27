package com.dbtechprojects.cloudstatustest.repository

import android.util.Log
import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import com.dbtechprojects.cloudstatustest.api.AzureApiInterface
import com.dbtechprojects.cloudstatustest.api.GcpApiInterface
import com.dbtechprojects.cloudstatustest.database.CacheDatabase
import com.dbtechprojects.cloudstatustest.model.AWSFeed
import com.dbtechprojects.cloudstatustest.model.AwsItem
import com.dbtechprojects.cloudstatustest.model.AzureFeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "Main Repository"

class MainRepository
@Inject
constructor(
    private var awsapi: AwsApiInterface,
    private var azureapi: AzureApiInterface,
    private var gcpapi: GcpApiInterface,
    private val database: CacheDatabase
) {

    fun fetchFromAwsApi(coroutineScope: CoroutineScope) {
        val call = awsapi.getAwsCall()
        call.enqueue(object : Callback<AWSFeed> {
            override fun onResponse(call: Call<AWSFeed>, response: Response<AWSFeed>) {
                if (response.isSuccessful) {
                    response.body()?.channel?.itemList?.let { putAwsItemsInDb(coroutineScope, it) }
                }
            }

            override fun onFailure(call: Call<AWSFeed>, t: Throwable) {
                Log.e(TAG, "onFailure: An error occurred while trying to fetch data from the API")
            }
        })
    }

    private fun putAwsItemsInDb(coroutineScope: CoroutineScope, awsItems: List<AwsItem>) {
        coroutineScope.launch {
            awsItems.forEach { getDatabaseDao().insertAwsItem(it) }
        }
    }

    suspend fun getAzureEvent(): Response<AzureFeed> = azureapi.getAzureEvent()

    fun getGcpEvent() = gcpapi.getGcpEvent()

    fun getDatabaseDao() = database.getDao()
}
