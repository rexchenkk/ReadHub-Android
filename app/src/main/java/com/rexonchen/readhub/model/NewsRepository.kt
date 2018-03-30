package com.rexonchen.readhub.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.provider.ContactsContract
import android.util.Log
import com.rexonchen.readhub.model.entity.News
import com.rexonchen.readhub.model.entity.NewsList
import com.rexonchen.readhub.utils.network.ReadHubApi
import com.rexonchen.readhub.utils.network.enqueue

/**
 * Created by rexonchen on 2018/3/23.
 */
class NewsRepository private constructor(private val mDatabase:AppDatabase){

    private val mObservableNews: MediatorLiveData<List<News>> = MediatorLiveData()
    private val mNewsList:MediatorLiveData<NewsList> = MediatorLiveData()
    private val error:MediatorLiveData<String> = MediatorLiveData()
    init {
        mObservableNews.addSource(mDatabase.newsDao().getAllNews()) { newsEntities ->
            if (mDatabase.getDatabaseCreated().value != null) {
                mObservableNews.postValue(newsEntities)
            }
        }
    getNewsList(System.currentTimeMillis())
    }



    fun getAllNews(): LiveData<List<News>> = mObservableNews

    fun getNews(NewsId:Long):LiveData<News> = mDatabase.newsDao().getNews(NewsId)

    fun getNewsList(lastCursor: Long = System.currentTimeMillis(), pageSize: Int = 10) {
        ReadHubApi.apiService.news(lastCursor, pageSize).enqueue {
            onResponse { _, response ->
                Log.d("NewsListViewModel", "onResponse:${response?.body()?.pageSize}")

                response?.let {
                    mNewsList.value = it.body()
                    it.body()?.data?.forEach {
                        mDatabase.newsDao().insertNews(it)
                    }
//                    DataGenerator.generateNews().forEach {
//                        mDatabase.newsDao().insertNews(it)
//                    }
//                    mObservableNews.postValue(DataGenerator.generateNews())
//                    mObservableNews.postValue(it.body()!!.data)
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


    companion object {

        private val sInstance: NewsRepository? = null

        fun getInstance(database: AppDatabase): NewsRepository =
                sInstance ?: Unit.let {
                    synchronized(NewsRepository::class.java) {
                        sInstance ?: NewsRepository(database)
                    }
                }
    }
}