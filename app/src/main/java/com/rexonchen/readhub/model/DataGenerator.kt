package com.rexonchen.readhub.model

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.rexonchen.readhub.model.entity.News
import com.rexonchen.readhub.model.entity.NewsList
import com.rexonchen.readhub.utils.network.ReadHubApi
import com.rexonchen.readhub.utils.network.enqueue

/**
 * Created by rexonchen on 2018/3/26.
 */
object DataGenerator {
    val newsList = MutableLiveData<NewsList>()
    val error = MutableLiveData<String>()
    fun generateNews():List<News>{
        val news= mutableListOf<News>()
        getNewsList(System.currentTimeMillis())
        return news
    }
    fun generateNewsList():MutableLiveData<NewsList>{
        val news= mutableListOf<News>()
        getNewsList(System.currentTimeMillis())
        return newsList
    }
    fun getNewsList(lastCursor: Long = System.currentTimeMillis(), pageSize: Int = 10) {
        ReadHubApi.apiService.news(lastCursor, pageSize).enqueue {
            onResponse { _, response ->
                Log.d("NewsListViewModel", "onResponse:${response?.body()?.pageSize}")
                response?.let {
                    newsList.value = it.body()
                }
            }
            onFailure { _, t ->
                if (t == null) {
                    error.value = "Unknow Error"
                } else {
                    Log.e("NewsListViewModel", t.localizedMessage)
                    error.value = t.toString()
                }
            }
        }
    }
}