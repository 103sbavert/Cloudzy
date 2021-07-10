package com.dbtechprojects.cloudzy.database

import androidx.room.*
import com.dbtechprojects.cloudzy.model.AwsItem
import com.dbtechprojects.cloudzy.model.GcpItem

@Dao
interface CloudStatusDAO {

    // AWS
    @Query("SELECT * FROM aws_items")
    suspend fun getAwsEvents(): List<AwsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAwsItems(item: List<AwsItem>)

    @Query("DELETE FROM aws_items")
    suspend fun deleteAllAwsItems()

    // GCP
    @Query("SELECT * FROM gcp_items")
    suspend fun getGcpEvents(): List<GcpItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGcpItems(item: List<GcpItem>)

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT id, updates FROM gcp_items WHERE id == :id")
    suspend fun getGcpEventUpdatesById(id: String): GcpItem

    @Query("DELETE FROM gcp_items")
    suspend fun deleteAllGcpItems()
}
