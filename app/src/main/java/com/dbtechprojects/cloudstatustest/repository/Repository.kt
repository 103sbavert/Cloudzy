package com.dbtechprojects.cloudstatustest.repository

import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import com.dbtechprojects.cloudstatustest.api.AzureApiInterface
import com.dbtechprojects.cloudstatustest.api.GcpApiInterface
import com.dbtechprojects.cloudstatustest.model.AWSFeed
import com.dbtechprojects.cloudstatustest.model.AzureFeed
import com.dbtechprojects.cloudstatustest.model.GcpFeed
import retrofit2.Response
import javax.inject.Inject

class Repository
@Inject
constructor(private var awsapi: AwsApiInterface, private var azureapi: AzureApiInterface, private var gcpapi: GcpApiInterface) {
    suspend fun getAwsEvent(): Response<AWSFeed> = awsapi.getAwsEvent()

    suspend fun getAzureEvent(): Response<AzureFeed> = azureapi.getAzureEvent()

    suspend fun getGcpEvent(): Response<GcpFeed> = gcpapi.getGcpEvent()
}
