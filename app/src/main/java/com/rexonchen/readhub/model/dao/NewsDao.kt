package com.rexonchen.readhub.model.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.rexonchen.readhub.model.entity.News

/**
 * Created by rexonchen on 2018/3/23.
 */
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news:News)
    @Query("SELECT * FROM news WHERE id= :newsId")
    fun getNews(newsId:Long):LiveData<News>
    @Query("SELECT * FROM news")
    fun getAllNews():LiveData<List<News>>
}