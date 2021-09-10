package com.dbtechprojects.cloudzy.api

import com.dbtechprojects.cloudzy.model.AzureFeed
import retrofit2.Response
import retrofit2.http.GET

interface AzureApiInterface {

    //@GET("/en-gb/status/feed/")
    @GET("/sbeve72/7759b3ce023f101df664ace16af62f46/raw/141e1300807036133cdb442e97ee8463c3959cbf/something.xml")
    suspend fun getAzureResponse(): Response<AzureFeed>
}


