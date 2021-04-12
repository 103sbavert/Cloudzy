package com.dbtechprojects.cloudstatustest.api



import com.dbtechprojects.cloudstatustest.model.AwsFeedItem
import retrofit2.Call
import retrofit2.http.GET

interface AwsApiInterface {
    @GET("/rss/all.rss")
    fun getAwsEvent(): Call<AwsFeedItem>?


}


