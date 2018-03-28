package com.rexonchen.readhub.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by rexonchen on 2018/3/21.
 */
@Entity(tableName = "news")
data class News(
        @PrimaryKey
        var id:Long,
        var title:String,
        var summary:String,
        var summaryAuto:String,
        var url:String,
        var mobileUrl:String?,
        var siteName:String,
        var siteSlug:String,
        var language: String,
        var authorName:String,
        var publishDate:String
)