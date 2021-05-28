package com.dbtechprojects.cloudstatustest.repository

import androidx.lifecycle.MutableLiveData
import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import com.dbtechprojects.cloudstatustest.api.AzureApiInterface
import com.dbtechprojects.cloudstatustest.api.GcpApiInterface
import com.dbtechprojects.cloudstatustest.database.CacheDatabase
import com.dbtechprojects.cloudstatustest.model.AwsItem
import com.dbtechprojects.cloudstatustest.model.GcpItem
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "Main Repository"

class MainRepository
@Inject
constructor(
    private var awsapi: AwsApiInterface,
    private var azureapi: AzureApiInterface,
    private var gcpapi: GcpApiInterface,
    private val cacheDtabase: CacheDatabase
) {
    enum class State {
        SUCCESS,
        FAILURE
    }

    val awsApiFetchResult by lazy { MutableLiveData<State>() }

    suspend fun fetchFromAwsApi() {
        withContext(IO) {
            try {
                awsapi.getAwsResponse().body()?.channel?.itemList?.let { putAwsItemsInDb(it) }
                awsApiFetchResult.postValue(State.SUCCESS)
            } catch (e: Exception) {
                awsApiFetchResult.postValue(State.FAILURE)
            }
        }
    }

    private suspend fun putAwsItemsInDb(awsItems: List<AwsItem>) {
        withContext(Default) {
            for (it in awsItems) {
                getCacheDatabaseDao().insertAwsItem(it)
            }
        }
    }

    private val gcpApiFetchResult by lazy { MutableLiveData<State>() }

    suspend fun fetchFromGcpApi() {
        withContext(IO) {
            try {
                gcpapi.getGcpResponse().body()?.let { putGcpItemsInDb(it) }
                gcpApiFetchResult.postValue(State.SUCCESS)
            } catch (e: Exception) {
                gcpApiFetchResult.postValue(State.FAILURE)
            }
        }
    }

    private suspend fun putGcpItemsInDb(gcpItems: List<GcpItem>) {
        withContext(Default) {
            for (it in gcpItems) {
                getCacheDatabaseDao().insertGcpItem(it)
            }
        }
    }

    fun getCacheDatabaseDao() = cacheDtabase.getDao()
}
