package com.rexonchen.readhub.model.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.rexonchen.readhub.model.entity.News
import com.rexonchen.readhub.model.entity.NewsList

/**
 * Created by rexonchen on 2018/3/23.
 */
@Dao
interface NewsListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsList(newsList:NewsList)
    @Query("SELECT * FROM newsList")
    fun getNewsList():LiveData<NewsList>
}