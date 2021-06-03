package com.dbtechprojects.cloudzy.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dbtechprojects.cloudzy.model.AwsItem
import com.dbtechprojects.cloudzy.model.GcpItem

@Dao
interface CloudStatusDAO {

    // AWS
    @Query("SELECT * FROM awsItems")
    suspend fun getAwsEvents(): List<AwsItem>

    @Query("SELECT * FROM awsItems")
    fun getAwsEventsLiveData(): LiveData<List<AwsItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAwsItem(item: List<AwsItem>)

    @Query("DELETE FROM awsItems")
    suspend fun deleteAllAwsItems()

    // AWS
    @Query("SELECT * FROM gcpItems")
    suspend fun getGcpEvents(): List<GcpItem>

    @Query("SELECT * FROM gcpItems")
    fun getGcpEventsLiveData(): LiveData<List<GcpItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGcpItem(item: List<GcpItem>)

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT id, updates FROM gcpItems WHERE id == :id")
    suspend fun getGcpEventUpdatesById(id: String): GcpItem

    @Query("DELETE FROM gcpItems")
    suspend fun deleteAllGcpItems()
}
