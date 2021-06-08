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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAwsItems(item: List<AwsItem>)

    @Query("DELETE FROM awsItems")
    suspend fun deleteAllAwsItems()

    // GCP
    @Query("SELECT * FROM gcpItems")
    suspend fun getGcpEvents(): List<GcpItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGcpItems(item: List<GcpItem>)

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT id, updates FROM gcpItems WHERE id == :id")
    suspend fun getGcpEventUpdatesById(id: String): GcpItem

    @Query("DELETE FROM gcpItems")
    suspend fun deleteAllGcpItems()
}
