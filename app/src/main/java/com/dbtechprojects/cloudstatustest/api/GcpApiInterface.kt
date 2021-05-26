package com.dbtechprojects.cloudstatustest.api

import com.dbtechprojects.cloudstatustest.model.GcpItem
import retrofit2.Response
import retrofit2.http.GET

interface GcpApiInterface {
    @GET("/incidents.json")
    suspend fun getGcpEvent(): Response<GcpItem>
}


