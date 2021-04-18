package com.dbtechprojects.cloudstatustest.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "rss")
data class AWSFeed(

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
    @PropertyElement
    val guid: String,
    @PropertyElement
    val description: String
)

/*@Xml(name = "guid")
data class Guid(
    @Attribute(name = "isPermaLink")
    val isPermaLink: String
)*/
