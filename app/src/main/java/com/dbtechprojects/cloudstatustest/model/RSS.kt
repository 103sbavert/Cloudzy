package com.dbtechprojects.cloudstatustest.model

import com.tickaroo.tikxml.annotation.*

@Xml(name = "rss")
data class RSS(
    @Attribute
    val version: String,
    @Element
    val channel: Channel
)

@Xml(name = "channel")
data class Channel(
    @PropertyElement
    val title: String,
    @PropertyElement
    val link: String,
    @PropertyElement
    val language: String,
    @PropertyElement
    val lastBuildDate: String,
    @PropertyElement
    val generator: String,
    @PropertyElement
    val description: String,
    @PropertyElement
    val ttl: String,
    @Element
    val itemList: List<AwsItem>
)

@Xml(name = "item")
data class AwsItem(
    @PropertyElement
    val title: String,
    @PropertyElement
    val link: String,
    @PropertyElement
    val pubDate: String,
    @Element
    val guid: Guid,
    @PropertyElement
    val description: String
)

@Xml(name = "guid")
data class Guid(
    @TextContent
    val text: String,
    @Attribute(name = "isPermaLink")
    val isPermaLink: Boolean
)
