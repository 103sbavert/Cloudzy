package com.dbtechprojects.cloudstatustest.api

import com.dbtechprojects.cloudstatustest.model.AWSFeed
import retrofit2.Response
import retrofit2.http.GET

interface AwsApiInterface {

    @GET("/rss/all.rss")
    suspend fun getAwsResponse(): Response<AWSFeed>
}


