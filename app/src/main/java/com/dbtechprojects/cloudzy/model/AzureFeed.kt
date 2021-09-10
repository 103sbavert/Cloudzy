package com.dbtechprojects.cloudzy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tickaroo.tikxml.annotation.*

//TO BE UPDATED
@Xml(name = "rss")
data class AzureFeed(

    @Element(name = "channel")
    val channel: AzureChannel? = null,

    @Attribute
    val version: String? = null
)


@Xml(name = "channel")
data class AzureChannel(

    @PropertyElement
    val title: String? = null,

    @PropertyElement
    val link: String? = null,

    @PropertyElement
    val description: String? = null,

    @PropertyElement
    val language: String? = null,

    @PropertyElement
    val lastBuildDate: String? = null,

    @Element
    val itemList: List<AzureItem>
)

@Xml(name = "item")
@Entity(tableName = "azure_items")
data class AzureItem(

    @PrimaryKey
    @ColumnInfo(name = "guid")
    @Element
    val guid: Guid,

    @ColumnInfo(name = "link")
    @PropertyElement
    val link: String? = null,

    @ColumnInfo(name = "categories")
    @Element
    val categories: List<AzureCategory>? = null,

    @ColumnInfo(name = "title")
    @PropertyElement
    val title: String? = null,

    @ColumnInfo(name = "description")
    @PropertyElement
    val description: String? = null,

    @ColumnInfo(name = "pubDate")
    @PropertyElement
    val pubDate: String? = null
)

@Xml(name = "category")
data class AzureCategory(

    @TextContent
    val category: String? = null
)


