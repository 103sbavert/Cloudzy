@file:Suppress("DEPRECATED_ANNOTATION")

package com.dbtechprojects.cloudstatustest.model

import org.simpleframework.xml.*

@Root(name = "rss version", strict = false)
data class AwsFeed(
    @field:Element(name = "channel")
    @param:Element(name = "channel")
    val channel: Channel
)

@Root(name = "Channel", strict = false)
data class Channel(
    @field:ElementList(entry = "item", inline = true)
    @param:ElementList(entry = "item", inline = true)
    val item: List<AwsItem>,
)

@Root(name = "item", strict = false)
data class AwsItem(

    @field:Element(name = "description")
    @param:Element(name = "description")
    val description: String,

//    @field:Element(name = "guid")
//    @param:Element(name = "guid")
//    val guid: Guid,

    @field:Element(name = "link")
    @param:Element(name = "link")
    val link: String,

    @field:Element(name = "pubDate")
    @param:Element(name = "pubDate")
    val pubDate: String,

    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String
)

//@Root(name = "guid", strict = false)
//data class Guid(
//
//    @field:Element(name = "__text")
//    @param:Element(name = "__text")
//    val __text: String,
//
//    @field:Element(name = "isPermaLink")
//    @param:Element(name = "isPermaLink")
//    val _isPermaLink: String
//)


//<title><![CDATA[Amazon Web Services Service Status]]></title>
//<link>http://status.aws.amazon.com/</link>
//<language>en-us</language>
//<lastBuildDate>Mon, 12 Apr 2021 00:05:36 PDT</lastBuildDate>
//<generator>AWS Service Health Dashboard RSS Generator</generator>
//<description><![CDATA[Amazon Web Services Service Status]]></description>
//<ttl>5</ttl>

