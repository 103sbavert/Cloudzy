package com.dbtechprojects.cloudstatustest.api

import com.dbtechprojects.cloudstatustest.model.AWSFeed
import com.dbtechprojects.cloudstatustest.model.AzureFeed
import com.dbtechprojects.cloudstatustest.model.GcpFeed
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface GcpApiInterface {
    @GET("/incidents.json")
    suspend fun getGcpEvent(): Response<GcpFeed>
}


