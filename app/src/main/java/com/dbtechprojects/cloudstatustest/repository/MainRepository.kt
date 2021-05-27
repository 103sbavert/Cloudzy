package com.dbtechprojects.cloudstatustest.repository

import androidx.lifecycle.MutableLiveData
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
    enum class State {
        SUCCESS,
        FAILURE
    }

    val awsApiFetchResult by lazy { MutableLiveData<State>() }

    fun fetchFromAwsApi(coroutineScope: CoroutineScope) {
        val call = awsapi.getAwsCall()
        call.enqueue(object : Callback<AWSFeed> {
            override fun onResponse(call: Call<AWSFeed>, response: Response<AWSFeed>) {
                if (response.isSuccessful) {
                    response.body()?.channel?.itemList?.let { putAwsItemsInDb(coroutineScope, it) }
                    awsApiFetchResult.value = State.SUCCESS
                } else awsApiFetchResult.value = State.FAILURE
            }

            override fun onFailure(call: Call<AWSFeed>, t: Throwable) {
                awsApiFetchResult.value = State.FAILURE
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
