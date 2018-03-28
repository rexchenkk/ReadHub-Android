package com.rexonchen.readhub.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.RequiresPermission
import android.util.Log
import com.rexonchen.readhub.BasicApp
import com.rexonchen.readhub.model.entity.News
import com.rexonchen.readhub.utils.network.ReadHubApi
import com.rexonchen.readhub.utils.network.enqueue

/**
 * Created by rexonchen on 2018/3/22.
 */
class NewsListViewModel(application: Application): AndroidViewModel(application) {
    val news:MediatorLiveData<List<News>> = MediatorLiveData()


    init {
        news.value = null
        val news = (application as BasicApp).getRepository()
                .getAllNews()
        this@NewsListViewModel.news.addSource(news, this@NewsListViewModel.news::setValue)
    }



}