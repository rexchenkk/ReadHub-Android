package com.rexonchen.readhub.model.entity

/**
 * Created by rexonchen on 2018/3/21.
 */
data class News(val id:Long,
                val title:String,
                val summary:String,
                val summaryAuto:String,
                val url:String,
                val mobileUrl:String?,
                val siteName:String,
                val siteSlug:String,
                val language: String,
                val authorName:String,
                val publishDate:String
)