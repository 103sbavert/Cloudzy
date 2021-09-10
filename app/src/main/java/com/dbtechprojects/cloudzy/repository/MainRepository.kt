package com.dbtechprojects.cloudzy.repository

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

    sealed class State() {
        class PASSED : State()
        class LOADING : State()
        class FAILED : State()
    }

    val awsApiFetchResult = MutableLiveData<State>()
    val gcpApiFetchResult = MutableLiveData<State>()
    val azureApiFetchResult = MutableLiveData<State>()

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
        awsApiFetchResult.postValue(State.LOADING())
        val oldList = getCacheDatabaseDao().getAwsEvents()
        val newList = getAwsItemsFromApi()
        val shouldUpdateDb: Boolean = compareLists(newList, oldList)
        if (shouldUpdateDb) {
            getCacheDatabaseDao().deleteAllAwsItems()
            getCacheDatabaseDao().insertAwsItems(newList)
        }
        awsApiFetchResult.postValue(State.PASSED())
        shouldUpdateDb
    } catch (e: Exception) {
        e.printStackTrace()
        awsApiFetchResult.postValue(State.FAILED())
        false
    }

    private suspend fun getGcpItemsFromApi() = withContext(IO) {
        return@withContext gcpApi.getGcpResponse().body()!!
    }

    suspend fun updateGcpDb() = try {
        gcpApiFetchResult.postValue(State.LOADING())
        val oldList = getCacheDatabaseDao().getGcpEvents()
        val newList = getGcpItemsFromApi()
        val shouldUpdateDb: Boolean = compareLists(newList, oldList)
        if (shouldUpdateDb) {
            getCacheDatabaseDao().deleteAllGcpItems()
            getCacheDatabaseDao().insertGcpItems(newList)
        }
        gcpApiFetchResult.postValue(State.PASSED())
        shouldUpdateDb
    } catch (e: Exception) {
        e.printStackTrace()
        gcpApiFetchResult.postValue(State.FAILED())
        false
    }

    private suspend fun getAzureItemsFromApi() = withContext(IO) {
        return@withContext azureApi.getAzureResponse().body()?.channel!!.itemList
    }

    suspend fun updateAzureDb() = try {
        azureApiFetchResult.postValue(State.LOADING())
        val oldList = getCacheDatabaseDao().getAzureEvents()
        val newList = getAzureItemsFromApi()
        val shouldUpdateDb: Boolean = compareLists(newList, oldList)
        if (shouldUpdateDb) {
            getCacheDatabaseDao().deleteAllAzureItems()
            getCacheDatabaseDao().insertAzureItems(newList)
        }
        azureApiFetchResult.postValue(State.PASSED())
        shouldUpdateDb
    } catch (e: Exception) {
        azureApiFetchResult.postValue(State.FAILED())
        false
    }
}
