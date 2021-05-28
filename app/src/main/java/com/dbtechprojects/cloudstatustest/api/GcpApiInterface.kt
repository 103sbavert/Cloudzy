package com.dbtechprojects.cloudstatustest.api

import com.dbtechprojects.cloudstatustest.model.GcpItem
import retrofit2.Call
import retrofit2.http.GET

interface GcpApiInterface {
    @GET("/incidents.json")
    fun getGcpEvent(): Call<List<GcpItem>>
}


