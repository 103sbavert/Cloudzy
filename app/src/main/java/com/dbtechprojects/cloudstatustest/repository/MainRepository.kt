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
    private val cacheDatabase: CacheDatabase
) {
    enum class State {
        SUCCESS,
        FAILURE
    }

    val awsApiFetchResult by lazy { MutableLiveData<State>() }

    suspend fun fetchFromAwsApi() {
        withContext(IO) {
            try {

                // fetch the results from the api and put each fetched item in the db
                awsapi.getAwsResponse().body()?.channel?.itemList?.let { putAwsItemsInDb(it) }

                // post State.SUCCESS if the task above is successfully completed
                awsApiFetchResult.postValue(State.SUCCESS)
            } catch (e: Exception) {

                // post State.Failure if the task above fails
                awsApiFetchResult.postValue(State.FAILURE)
            }
        }
    }

    // put each item from the list in the db one by one using the method from the dao (duplicate items are ignored)
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

                // fetch the results from the api and put each fetched item in the db
                gcpapi.getGcpResponse().body()?.let { putGcpItemsInDb(it) }

                // post State.SUCCESS if the task above is successfully completed
                gcpApiFetchResult.postValue(State.SUCCESS)
            } catch (e: Exception) {

                // post State.Failure if the task above fails
                gcpApiFetchResult.postValue(State.FAILURE)
            }
        }
    }

    // put each item from the list in the db one by one using the method from the dao (duplicate items are ignored)
    private suspend fun putGcpItemsInDb(gcpItems: List<GcpItem>) {
        withContext(Default) {
            for (it in gcpItems) {
                getCacheDatabaseDao().insertGcpItem(it)
            }
        }
    }

    fun getCacheDatabaseDao() = cacheDatabase.getDao()
}
