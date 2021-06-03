package com.dbtechprojects.cloudzy.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dbtechprojects.cloudzy.api.AwsApiInterface
import com.dbtechprojects.cloudzy.api.AzureApiInterface
import com.dbtechprojects.cloudzy.api.GcpApiInterface
import com.dbtechprojects.cloudzy.database.CacheDatabase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "Main Repository"

class MainRepository
@Inject
constructor(
    private var awsApi: AwsApiInterface,
    private var azureApi: AzureApiInterface,
    private var gcpApi: GcpApiInterface,
    private val cacheDatabase: CacheDatabase
) {
    enum class State {
        SUCCESS,
        LOADING,
        FAILURE
    }

    private val _awsApiFetchResult = MutableLiveData<State>()
    val awsApiFetchResult: LiveData<State>
        get() = _awsApiFetchResult

    suspend fun fetchFromAwsApi() {
        withContext(IO) {
            try {
                _awsApiFetchResult.postValue(State.LOADING)

                // fetch the results from the api and put each fetched item in the db
                awsApi.getAwsResponse().body()?.channel?.itemList?.let { getCacheDatabaseDao().insertAwsItem(it) }

                // post State.SUCCESS if the task above is successfully completed
                _awsApiFetchResult.postValue(State.SUCCESS)
            } catch (e: Exception) {

                // post State.Failure if the task above fails
                _awsApiFetchResult.postValue(State.FAILURE)
            }
        }
    }

    private val _gcpApiFetchResult = MutableLiveData<State>()
    val gcpApiFetchResult: LiveData<State>
        get() = _gcpApiFetchResult

    suspend fun fetchFromGcpApi() {
        withContext(IO) {
            try {
                _gcpApiFetchResult.postValue(State.LOADING)

                // fetch the results from the api and put each fetched item in the db
                gcpApi.getGcpResponse().body()?.let { getCacheDatabaseDao().insertGcpItem(it) }

                // post State.SUCCESS if the task above is successfully completed
                _gcpApiFetchResult.postValue(State.SUCCESS)
            } catch (e: Exception) {

                // post State.Failure if the task above fails
                _gcpApiFetchResult.postValue(State.FAILURE)
            }
        }
    }


    fun getCacheDatabaseDao() = cacheDatabase.getDao()
}
