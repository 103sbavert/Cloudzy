package com.dbtechprojects.cloudstatustest.repository

import android.content.Context
import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import com.dbtechprojects.cloudstatustest.api.AzureApiInterface
import com.dbtechprojects.cloudstatustest.api.GcpApiInterface
import com.dbtechprojects.cloudstatustest.database.CacheDatabase
import com.dbtechprojects.cloudstatustest.model.AWSFeed
import com.dbtechprojects.cloudstatustest.model.AzureFeed
import com.dbtechprojects.cloudstatustest.model.GcpItem
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class Repository
@Inject
constructor(private var awsapi: AwsApiInterface, private var azureapi: AzureApiInterface, private var gcpapi: GcpApiInterface, private val database: CacheDatabase) {
    suspend fun getAwsEvent(): Response<AWSFeed> = awsapi.getAwsEvent()

    suspend fun getAzureEvent(): Response<AzureFeed> = azureapi.getAzureEvent()

    suspend fun getGcpEvent(): Response<GcpItem> = gcpapi.getGcpEvent()

    fun getDatabase() = database
}
