package com.rexonchen.readhub.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.rexonchen.readhub.model.entity.News
import com.rexonchen.readhub.model.entity.NewsList

/**
 * Created by rexonchen on 2018/3/23.
 */
class NewsRepository private constructor(private val mDatabase:AppDatabase){

    private val mObservableNews: MediatorLiveData<List<News>> = MediatorLiveData()
    private val mObservableNewsList:MediatorLiveData<NewsList> = MediatorLiveData()
    init {
        mObservableNews.addSource(mDatabase.newsDao().getAllNews()) { newsEntities ->
            if (mDatabase.getDatabaseCreated().value != null) {
                mObservableNews.postValue(newsEntities)
            }
        }
        mObservableNewsList.addSource(mDatabase.newsListDao().getNewsList()){ newNewsList->
            mObservableNewsList.postValue(newNewsList)
        }
    }

    fun getAllNews(): LiveData<List<News>> = mObservableNews

    fun getNews(NewsId:Long):LiveData<News> = mDatabase.newsDao().getNews(NewsId)

    fun getNewsList():LiveData<NewsList> = mObservableNewsList


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