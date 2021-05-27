package com.dbtechprojects.cloudstatustest.repository

import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import com.dbtechprojects.cloudstatustest.api.AzureApiInterface
import com.dbtechprojects.cloudstatustest.api.GcpApiInterface
import com.dbtechprojects.cloudstatustest.database.CacheDatabase
import com.dbtechprojects.cloudstatustest.model.AzureFeed
import retrofit2.Response
import javax.inject.Inject

class Repository
@Inject
constructor(
    private var awsapi: AwsApiInterface,
    private var azureapi: AzureApiInterface,
    private var gcpapi: GcpApiInterface,
    private val database: CacheDatabase
) {
    fun getAwsCall() = awsapi.getAwsCall()

    suspend fun getAzureEvent(): Response<AzureFeed> = azureapi.getAzureEvent()

    fun getGcpEvent() = gcpapi.getGcpEvent()

    fun getDatabaseDao() = database.cloudStatusDao()
}
