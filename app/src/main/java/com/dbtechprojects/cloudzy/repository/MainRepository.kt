package com.dbtechprojects.cloudzy.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dbtechprojects.cloudzy.api.AwsApiInterface
import com.dbtechprojects.cloudzy.api.AzureApiInterface
import com.dbtechprojects.cloudzy.api.GcpApiInterface
import com.dbtechprojects.cloudzy.database.CacheDatabase
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

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

    private val _gcpApiFetchResult = MutableLiveData<State>()
    val gcpApiFetchResult: LiveData<State>
        get() = _gcpApiFetchResult

    fun getCacheDatabaseDao() = cacheDatabase.getDao()

    private suspend fun <T> compareLists(newList: List<T>, oldList: List<T>): Boolean {
        return withContext(Default) {
            return@withContext newList != oldList
        }
    }

    private suspend fun getAwsItemsFromApi() = withContext(IO) {
        return@withContext awsApi.getAwsResponse().body()?.channel?.itemList!!
    }

    suspend fun updateAwsDb() = try {
        _awsApiFetchResult.postValue(State.LOADING)
        val oldList = getCacheDatabaseDao().getAwsEvents()
        val newList = getAwsItemsFromApi()
        val shouldUpdateDb: Boolean = compareLists(newList, oldList)
        if (shouldUpdateDb) {
            getCacheDatabaseDao().deleteAllAwsItems()
            getCacheDatabaseDao().insertAwsItems(newList)
        }
        _awsApiFetchResult.postValue(State.SUCCESS)
        shouldUpdateDb
    } catch (e: Exception) {
        Log.e("updateAwsDb", e.localizedMessage)
        _awsApiFetchResult.postValue(State.FAILURE)
        false
    }

    private suspend fun getGcpItemsFromApi() = withContext(IO) {
        return@withContext gcpApi.getGcpResponse().body()!!
    }

    suspend fun updateGcpDb() = try {
        _gcpApiFetchResult.postValue(State.LOADING)
        val oldList = getCacheDatabaseDao().getGcpEvents()
        val newList = getGcpItemsFromApi()
        val shouldUpdateDb: Boolean = compareLists(newList, oldList)
        if (shouldUpdateDb) {
            getCacheDatabaseDao().deleteAllGcpItems()
            getCacheDatabaseDao().insertGcpItems(newList)
        }
        _gcpApiFetchResult.postValue(State.SUCCESS)
        shouldUpdateDb
    } catch (e: Exception) {
        Log.e("updateGcpDb", e.localizedMessage)
        _gcpApiFetchResult.postValue(State.FAILURE)
        false
    }
}
