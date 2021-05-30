package com.dbtechprojects.cloudstatustest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tickaroo.tikxml.annotation.*

@Xml(name = "rss")
data class AWSFeed(

    @Attribute
    val version: String? = null,

    @Element
    val channel: Channel? = null
)

@Xml(name = "channel")
data class Channel(

    @PropertyElement
    val title: String? = null,

    @PropertyElement
    val link: String? = null,

    @PropertyElement
    val language: String? = null,

    @PropertyElement
    val lastBuildDate: String? = null,

    @PropertyElement
    val generator: String? = null,

    @PropertyElement
    val description: String? = null,

    @PropertyElement
    val ttl: String? = null,

    @Element
    val itemList: List<AwsItem>? = null
)

@Xml(name = "item")
@Entity(tableName = "awsItems")
data class AwsItem(

    @ColumnInfo(name = "title")
    @PropertyElement
    val title: String? = null,

    @ColumnInfo(name = "link")
    @PropertyElement
    val link: String? = null,

    @ColumnInfo(name = "pubDate")
    @PropertyElement
    val pubDate: String? = null,

    // guid is always unique
    @PrimaryKey
    @ColumnInfo(name = "guid")
    @Element
    val guid: Guid,

    @PropertyElement
    @ColumnInfo(name = "desc")
    val description: String? = null,
)

@Xml(name = "guid")
data class Guid(
    @TextContent
    val text: String,

    @Attribute(name = "isPermaLink")
    val isPermaLink: Boolean
)
