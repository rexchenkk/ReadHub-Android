package com.rexonchen.readhub.model

import com.rexonchen.readhub.model.entity.News
import com.rexonchen.readhub.model.entity.NewsList

/**
 * Created by rexonchen on 2018/3/26.
 */
object DataGenerator {
    private val new1=News(
            1L,
            "King James",
            "Lebron James got 60 points last night",
            "Lebron James got 60 points last night",
            "https://hupu.com",
            "https://m.hupu.com",
            "HUPU",
            "hupu-china",
            "Chinese",
            "Yang Yi",
            "2018-03-26")
    private val new2=News(
            2L,
            "King James",
            "Lebron James got 60 points last night",
            "Lebron James got 60 points last night",
            "https://hupu.com",
            "https://m.hupu.com",
            "HUPU",
            "hupu-china",
            "Chinese",
            "Yang Yi",
            "2018-03-26")
    private val new3=News(
            3L,
            "King James",
            "Lebron James got 60 points last night",
            "Lebron James got 60 points last night",
            "https://hupu.com",
            "https://m.hupu.com",
            "HUPU",
            "hupu-china",
            "Chinese",
            "Yang Yi",
            "2018-03-26")
    fun generateNews():List<News>{

        val news= mutableListOf<News>()

        news.add(new1)
        news.add(new2)
        news.add(new3)
        return news
    }
    fun generateNewsList():NewsList{
        val news= mutableListOf<News>()
        news.add(new1)
        news.add(new2)
        news.add(new3)
        val newsList=NewsList(news,10,3,1)
        return newsList
    }
}