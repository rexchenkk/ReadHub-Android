package com.rexonchen.readhub.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import com.rexonchen.readhub.BasicApp
import com.rexonchen.readhub.model.entity.News

/**
 * Created by rexonchen on 2018/3/22.
 */
class NewsListViewModel(application: Application): AndroidViewModel(application) {
    val news: MediatorLiveData<List<News>> = MediatorLiveData()

    init {

        // set by default null, until we get data from the database.
        news.value = null

        val news = (application as BasicApp).getRepository()
                .getNewsList()

        // observe the changes of the products from the database and forward them
//        this@NewsListViewModel.news.addSource(products, this@ProductListViewModel.products::setValue)
    }
}