package com.dbtechprojects.cloudzy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "gcp_items")
data class GcpItem(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName(value = "id")
    var id: String,

    @ColumnInfo(name = "number")
    @SerializedName(value = "number")
    var number: String? = null,

    @ColumnInfo(name = "begin")
    @SerializedName(value = "begin")
    var begin: String? = null,

    @ColumnInfo(name = "created")
    @SerializedName(value = "created")
    var created: String? = null,

    @ColumnInfo(name = "end")
    @SerializedName(value = "end")
    var end: String? = null,

    @ColumnInfo(name = "modified")
    @SerializedName(value = "modified")
    var modified: String? = null,

    @ColumnInfo(name = "external_desc")
    @SerializedName(value = "external_desc")
    var externalDesc: String? = null,

    @ColumnInfo(name = "updates")
    @SerializedName(value = "updates")
    var updates: List<Update>? = null,

    @ColumnInfo(name = "most_recent_update")
    @SerializedName(value = "most_recent_update")
    var mostRecentUpdate: MostRecentUpdate? = null,

    @ColumnInfo(name = "status_impact")
    @SerializedName(value = "status_impact")
    var statusImpact: String? = null,

    @ColumnInfo(name = "severity")
    @SerializedName(value = "severity")
    var severity: String? = null,

    @ColumnInfo(name = "service_key")
    @SerializedName("service_key")
    var serviceKey: String? = null,

    @ColumnInfo(name = "service_name")
    @SerializedName(value = "service_name")
    var serviceName: String? = null,

    @ColumnInfo(name = "affected_products")
    @SerializedName(value = "affected_products")
    var affectedProducts: List<AffectedProduct>? = null,

    @ColumnInfo(name = "uri")
    @SerializedName(value = "uri")
    var uri: String? = null
)

data class AffectedProduct(

    @ColumnInfo(name = "title")
    @SerializedName(value = "title")
    var title: String? = null,

    @ColumnInfo(name = "id")
    @SerializedName(value = "id")
    var id: String? = null
)

data class MostRecentUpdate(

    @SerializedName(value = "created")
    @ColumnInfo(name = "created")
    var created: String? = null,

    @ColumnInfo(name = "modified")
    @SerializedName(value = "modified")
    var modified: String? = null,

    @ColumnInfo(name = "when")
    @SerializedName(value = "when")
    var `when`: String? = null,

    @ColumnInfo(name = "text")
    @SerializedName(value = "text")
    var text: String? = null,

    @ColumnInfo(name = "status")
    @SerializedName(value = "status")
    var status: String? = null
)

data class Update(

    @SerializedName(value = "created")
    var created: String? = null,

    @SerializedName(value = "modified")
    var modified: String? = null,

    @SerializedName(value = "when")
    var `when`: String? = null,

    @SerializedName(value = "text")
    var text: String? = null,

    @SerializedName(value = "status")
    var status: String? = null
)
