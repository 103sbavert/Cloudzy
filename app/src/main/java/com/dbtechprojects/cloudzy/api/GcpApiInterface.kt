package com.dbtechprojects.cloudzy.api

import com.dbtechprojects.cloudzy.model.GcpItem
import retrofit2.Response
import retrofit2.http.GET

interface GcpApiInterface {

    @GET("/incidents.json")
    suspend fun getGcpResponse(): Response<List<GcpItem>>
}


