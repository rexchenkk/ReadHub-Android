package com.rexonchen.readhub.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rexonchen.readhub.R
import com.rexonchen.readhub.model.entity.News
import com.rexonchen.readhub.viewmodel.NewsListViewModel
import com.rexonchen.readhub.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.news_detail.*
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

/**
 * Created by rexonchen on 2018/3/27.
 */
class NewsFragment:Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            =inflater.inflate(R.layout.news_detail,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory=NewsViewModel.Factory(activity.application,arguments.getLong(KEY_NEWS_ID))
        val model=ViewModelProviders.of(this,factory).get(NewsViewModel::class.java)
        val viewModel=ViewModelProviders.of(this).get(NewsListViewModel::class.java)
        subscribeToModel(model)

    }

    private fun subscribeToModel(model: NewsViewModel) {
//        Log.d("NewsFragment","${model.observableNews.value?.id}")
        model.observableNews.observe(this, Observer<News> { news ->
            news?.let {
                Log.d("NewsFragment","${it.id}")
                news_detail_title.text = it.title
                news_detail_tips.text ="${it.siteName} on ${it.publishDate}"
                news_detail_text.text = it.summary
            }
        })
//        model.news.value.let {
//            Log.d("NewsFragment","${it?.size} }")
//            news_detail_title.text = it?.find { it.id==arguments.getLong(KEY_NEWS_ID) }?.title
//            news_detail_tips.text = it?.find { it.id==arguments.getLong(KEY_NEWS_ID) }?.siteName
//            news_detail_text.text = it?.find { it.id==arguments.getLong(KEY_NEWS_ID) }?.summary
//        }
//        model.newsTransfer.observe(this, Observer<News> {
//            Log.d("NewsFragment","${it.toString()}")
//        })
//        Log.d("NewsFragment","${model.newsTransfer.value}")
    }
    companion object {
        val KEY_NEWS_ID = "news_id"
        fun forNews(NewsId:Long): NewsFragment =
                NewsFragment().apply {
                    arguments = Bundle().also { it.putLong(KEY_NEWS_ID, NewsId) }
//                    Log.d("NewsFragment","Created and id is ${NewsId}")
                }
    }
}