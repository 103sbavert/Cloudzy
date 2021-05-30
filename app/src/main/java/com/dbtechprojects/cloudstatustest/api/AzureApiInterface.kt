package com.dbtechprojects.cloudstatustest.api

import com.dbtechprojects.cloudstatustest.model.AzureFeed
import retrofit2.Response
import retrofit2.http.GET

interface AzureApiInterface {

    @GET("/en-gb/status/feed/")
    suspend fun getAzureEvent(): Response<AzureFeed>
}


