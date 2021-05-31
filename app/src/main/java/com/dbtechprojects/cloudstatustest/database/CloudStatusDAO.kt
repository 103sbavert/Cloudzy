package com.dbtechprojects.cloudstatustest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dbtechprojects.cloudstatustest.model.AwsItem
import com.dbtechprojects.cloudstatustest.model.GcpItem

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

    @Query("DELETE FROM gcpItems")
    suspend fun deleteAllGcpItems()
}
