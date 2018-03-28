package com.rexonchen.readhub.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rexonchen.readhub.BasicApp
import com.rexonchen.readhub.model.NewsRepository
import com.rexonchen.readhub.model.entity.News

/**
 * Created by rexonchen on 2018/3/22.
 */
class NewsViewModel(application: Application,repository: NewsRepository,
                    private val mNewsId:Long):AndroidViewModel(application) {
    val observableNews:LiveData<News> by lazy { repository.getNews(mNewsId) }

    class Factory(private val mApplication:Application,private val mNewsId:Long):ViewModelProvider.NewInstanceFactory(){
        private val mRepository:NewsRepository by lazy { (mApplication as BasicApp).getRepository() }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T
                = NewsViewModel(mApplication,mRepository,mNewsId) as T
    }
}