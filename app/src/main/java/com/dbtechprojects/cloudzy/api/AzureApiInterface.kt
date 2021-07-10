package com.dbtechprojects.cloudzy.api

import com.dbtechprojects.cloudzy.model.AzureFeed
import retrofit2.Response
import retrofit2.http.GET

interface AzureApiInterface {

    //@GET("/en-gb/status/feed/")
    @GET("/dbtechprojects.com/Azure.rss")
    suspend fun getAzureResponse(): Response<AzureFeed>
}


