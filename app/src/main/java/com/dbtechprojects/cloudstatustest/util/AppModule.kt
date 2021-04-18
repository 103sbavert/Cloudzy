package com.dbtechprojects.cloudstatustest.util

import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import com.dbtechprojects.cloudstatustest.repository.Repository
import com.dbtechprojects.cloudstatustest.util.Constants.BASE_URL
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAwsApi(): AwsApiInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(TikXmlConverterFactory.create())
        .build()
        .create(AwsApiInterface::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api: AwsApiInterface) = Repository(api)
}