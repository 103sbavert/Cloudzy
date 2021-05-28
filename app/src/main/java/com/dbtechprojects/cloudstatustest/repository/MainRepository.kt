package com.dbtechprojects.cloudstatustest.repository

import androidx.lifecycle.MutableLiveData
import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import com.dbtechprojects.cloudstatustest.api.AzureApiInterface
import com.dbtechprojects.cloudstatustest.api.GcpApiInterface
import com.dbtechprojects.cloudstatustest.database.CacheDatabase
import com.dbtechprojects.cloudstatustest.model.AwsItem
import com.dbtechprojects.cloudstatustest.model.AzureFeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
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

    suspend fun fetchFromAwsApi() {
        withContext(IO) {
            try {
                awsapi.getAwsCall().body()?.channel?.itemList?.let { putAwsItemsInDb(it) }
                awsApiFetchResult.postValue(State.SUCCESS)
            } catch (e: Exception) {
                awsApiFetchResult.postValue(State.FAILURE)
            }
        }
    }

    private suspend fun putAwsItemsInDb(awsItems: List<AwsItem>) {
        withContext(Default) {
            for (it in awsItems) {
                getDatabaseDao().insertAwsItem(it)
            }
        }
    }

    suspend fun getAzureEvent(): Response<AzureFeed> = azureapi.getAzureEvent()

    fun getGcpEvent() = gcpapi.getGcpEvent()

    fun getDatabaseDao() = database.getDao()
}
