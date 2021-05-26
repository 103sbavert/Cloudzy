package com.dbtechprojects.cloudstatustest.model

import com.google.gson.annotations.SerializedName

data class GcpItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("begin")
    val begin: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("end")
    val end: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("external_desc")
    val externalDesc: String,
    @SerializedName("updates")
    val updates: List<Update>,
    @SerializedName("most_recent_update")
    val mostRecentUpdate: MostRecentUpdate,
    @SerializedName("status_impact")
    val statusImpact: String,
    @SerializedName("severity")
    val severity: String,
    @SerializedName("service_name")
    val serviceName: String,
    @SerializedName("affected_products")
    val affectedProducts: List<AffectedProduct>,
    @SerializedName("uri")
    val uri: String
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
