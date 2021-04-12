package com.dbtechprojects.cloudstatustest.repository

import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import javax.inject.Inject

class Repository @Inject constructor(private var api: AwsApiInterface) {
    suspend fun getAwsEvent() = api.getAwsEvent()?.execute()
}