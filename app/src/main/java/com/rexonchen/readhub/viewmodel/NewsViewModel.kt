package com.rexonchen.readhub.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.rexonchen.readhub.model.NewsRepository
import com.rexonchen.readhub.model.entity.News

/**
 * Created by rexonchen on 2018/3/22.
 */
class NewsViewModel(application: Application,repository: NewsRepository,
                    private val mNewsId:Long):AndroidViewModel(application) {
    val observableNews:LiveData<News> by lazy { repository.getNews(mNewsId) }

}