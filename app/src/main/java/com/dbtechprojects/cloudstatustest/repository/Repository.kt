package com.dbtechprojects.cloudstatustest.repository

import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import com.dbtechprojects.cloudstatustest.model.AwsFeed
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private var api: AwsApiInterface) {
    fun getAwsEvent(): Response<AwsFeed> = api.getAwsEvent().execute()
}
