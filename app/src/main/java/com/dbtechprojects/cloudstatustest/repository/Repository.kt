package com.dbtechprojects.cloudstatustest.repository

import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import com.dbtechprojects.cloudstatustest.model.RSS
import retrofit2.Response
import javax.inject.Inject

class Repository
@Inject
constructor(private var api: AwsApiInterface) {
    fun getAwsEvent(): Response<RSS> = api.getAwsEvent().execute()
}
