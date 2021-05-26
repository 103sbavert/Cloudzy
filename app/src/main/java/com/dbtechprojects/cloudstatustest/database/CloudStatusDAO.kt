package com.dbtechprojects.cloudstatustest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dbtechprojects.cloudstatustest.model.AwsItem

@Dao
interface CloudStatusDAO {

    // AWS
    @Query("SELECT * FROM awsItems")
    fun getAwsEvents(): LiveData<List<AwsItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAwsItem(item: AwsItem)

    @Query("DELETE FROM awsItems")
    suspend fun deleteAllAwsItems()

/*    // GCP
    @Query("SELECT * FROM gcpItems")
    fun getGcpEvents(): Flow<List<GcpDbItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGcpItem(item: GcpDbItem)

    @Query("DELETE FROM gcpItems")
    suspend fun deleteAllGcpItems()*/

    // WorkManager Queries

    // first AWS Event
    @Query("SELECT * FROM awsItems LIMIT :limit")
    fun getLatestAwsEvent(limit: Int): List<AwsItem>

/*    // first GCP Feed
    @Query("SELECT * FROM gcpItems LIMIT :limit")
    fun getLatestGcpEvent(limit: Int): List<GcpDbItem>*/
}
