package com.dbtechprojects.cloudzy.api

import com.dbtechprojects.cloudzy.model.AwsFeed
import retrofit2.Response
import retrofit2.http.GET

interface AwsApiInterface {

    @GET("/rss/all.rss")
    suspend fun getAwsResponse(): Response<AwsFeed>
}


