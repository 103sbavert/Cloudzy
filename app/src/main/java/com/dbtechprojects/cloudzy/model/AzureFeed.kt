package com.dbtechprojects.cloudzy.model

import com.tickaroo.tikxml.annotation.*

//TO BE UPDATED
@Xml(name = "rss")
data class AzureFeed(

    @Element
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
    val item: List<AzureItem>? = null,
)

@Xml(name = "item")
data class AzureItem(

    @Element
    val guid: AzureGuid,

    @PropertyElement
    val link: String? = null,

    @Element
    val category: List<AzureCategory>? = null,

    @PropertyElement
    val title: String? = null,

    @PropertyElement
    val description: String? = null,

    @PropertyElement
    val pubDate: String? = null
)

@Xml(name = "category")
data class AzureCategory(
    val category: String? = null
)

@Xml(name = "guid")
data class AzureGuid (

    @Attribute(name = "isPermaLink")
    val isPermaLink: Boolean? = false,

    @TextContent
    val text: String? = null
)


