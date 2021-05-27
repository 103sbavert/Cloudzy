package com.dbtechprojects.cloudstatustest.util

import android.content.Context
import com.dbtechprojects.cloudstatustest.api.AwsApiInterface
import com.dbtechprojects.cloudstatustest.api.AzureApiInterface
import com.dbtechprojects.cloudstatustest.api.GcpApiInterface
import com.dbtechprojects.cloudstatustest.database.CacheDatabase
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import com.dbtechprojects.cloudstatustest.util.Constants.AWS_URL
import com.dbtechprojects.cloudstatustest.util.Constants.AZURE_URL
import com.dbtechprojects.cloudstatustest.util.Constants.GCP_URL
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAwsApi(): AwsApiInterface = Retrofit.Builder()
        .baseUrl(AWS_URL)
        .addConverterFactory(TikXmlConverterFactory.create())
        .build()
        .create(AwsApiInterface::class.java)

    @Singleton
    @Provides
    fun provideAzureApi(): AzureApiInterface = Retrofit.Builder()
        .baseUrl(AZURE_URL)
        .addConverterFactory(TikXmlConverterFactory.create())
        .build()
        .create(AzureApiInterface::class.java)

    @Singleton
    @Provides
    fun provideGcpApi(): GcpApiInterface = Retrofit.Builder()
        .baseUrl(GCP_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GcpApiInterface::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(awsApi: AwsApiInterface, azureApi: AzureApiInterface, gcpApi: GcpApiInterface, database: CacheDatabase) = MainRepository(awsApi, azureApi, gcpApi, database)

    @Provides
    fun provideCacheDb(@ApplicationContext context: Context): CacheDatabase {
        return CacheDatabase.getDatabase(context)
    }
}
