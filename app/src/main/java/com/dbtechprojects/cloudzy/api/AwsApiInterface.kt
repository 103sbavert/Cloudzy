package com.dbtechprojects.cloudzy.api

import com.dbtechprojects.cloudzy.model.AWSFeed
import retrofit2.Response
import retrofit2.http.GET

interface AwsApiInterface {

    @GET("/rss/all.rss")
    suspend fun getAwsResponse(): Response<AWSFeed>
}


