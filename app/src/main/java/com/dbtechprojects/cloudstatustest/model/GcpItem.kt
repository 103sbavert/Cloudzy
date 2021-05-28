package com.dbtechprojects.cloudstatustest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


// table title items must be unique this stops us storing duplicate items
@Entity(tableName = "gcpItems")
data class GcpItem(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String, // custom name as ID will be reserved for Room

    @ColumnInfo(name = "number")
    @SerializedName("number")
    val number: String? = null,

    @ColumnInfo(name = "begin")
    @SerializedName("begin")
    val begin: String? = null,

    @ColumnInfo(name = "created")
    @SerializedName("created")
    val created: String? = null,

    @ColumnInfo(name = "end")
    @SerializedName("end")
    val end: String? = null,

    @ColumnInfo(name = "modified")
    @SerializedName("modified")
    val modified: String? = null,

    @ColumnInfo(name = "external_desc")
    @SerializedName("external_desc")
    val externalDesc: String? = null,

    @ColumnInfo(name = "updates")
    @SerializedName("updates")
    val updates: List<Update>? = null,

    @ColumnInfo(name = "most_recent_update")
    @SerializedName("most_recent_update")
    val mostRecentUpdate: MostRecentUpdate? = null,

    @ColumnInfo(name = "status_impact")
    @SerializedName("status_impact")
    val statusImpact: String? = null,

    @ColumnInfo(name = "severity")
    @SerializedName("severity")
    val severity: String? = null,

    @ColumnInfo(name = "service_name")
    @SerializedName("service_name")
    val serviceName: String? = null,

    @ColumnInfo(name = "affected_products")
    @SerializedName("affected_products")
    val affectedProducts: List<AffectedProduct>? = null,

    @ColumnInfo(name = "uri")
    @SerializedName("uri")
    val uri: String? = null,
)

data class AffectedProduct(

    @SerializedName("title")
    val title: String
)

data class MostRecentUpdate(

    @SerializedName("created")
    val created: String,

    @SerializedName("modified")
    val modified: String,

    @SerializedName("when")
    val `when`: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("status")
    val status: String
)

data class Update(

    @SerializedName("created")
    val created: String,

    @SerializedName("modified")
    val modified: String,

    @SerializedName("when")
    val `when`: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("status")
    val status: String
)
