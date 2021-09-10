package com.dbtechprojects.cloudzy.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dbtechprojects.cloudzy.model.AwsItem
import com.dbtechprojects.cloudzy.model.AzureItem
import com.dbtechprojects.cloudzy.model.GcpItem

@Dao
interface CloudStatusDAO {

    // AWS
    @Query("SELECT * FROM aws_items")
    suspend fun getAwsEvents(): List<AwsItem>

    @Query("SELECT * FROM aws_items")
    fun getAwsEventsLiveData(): LiveData<List<AwsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAwsItems(item: List<AwsItem>)

    @Query("DELETE FROM aws_items")
    suspend fun deleteAllAwsItems()

    // GCP
    @Query("SELECT * FROM gcp_items")
    suspend fun getGcpEvents(): List<GcpItem>

    @Query("SELECT * FROM gcp_items")
    fun getGcpEventsLiveData(): LiveData<List<GcpItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGcpItems(item: List<GcpItem>)

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT id, updates FROM gcp_items WHERE id == :id")
    suspend fun getGcpEventUpdatesById(id: String): GcpItem

    @Query("DELETE FROM gcp_items")
    suspend fun deleteAllGcpItems()

    // Azure
    @Query("SELECT * FROM azure_items")
    suspend fun getAzureEvents(): List<AzureItem>

    @Query("SELECT * FROM azure_items")
    fun getAzureEventsLiveData(): LiveData<List<AzureItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAzureItems(item: List<AzureItem>)

    @Query("DELETE FROM azure_items")
    suspend fun deleteAllAzureItems()
}
