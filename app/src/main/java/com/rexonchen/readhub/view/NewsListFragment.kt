package com.rexonchen.readhub.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rexonchen.readhub.R
import com.rexonchen.readhub.model.entity.News
import com.rexonchen.readhub.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.news_list.*
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

/**
 * Created by rexonchen on 2018/3/22.
 */
class NewsListFragment:Fragment(){
    private val mNewsAdapter:NewsAdapter by lazy { NewsAdapter(fragmentManager) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.news_list,container,false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        news_list_tv.layoutManager=LinearLayoutManager(context)
        news_list_tv.adapter=mNewsAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(NewsListViewModel::class.java)
//        mNewsAdapter.setViewModel(viewModel)
        subscribeUi(viewModel)
    }

    private fun subscribeUi(viewModel:NewsListViewModel){
        viewModel.news.observe(this, Observer<List<News>>{News->
            if(News!=null){
                mNewsAdapter.newsList=News
                loading_tv.visibility=View.GONE
                news_list_tv.visibility=View.VISIBLE
            }else{
                loading_tv.visibility=View.VISIBLE
                news_list_tv.visibility=View.GONE
            }
        })
    }
    companion object {
        val TAG = "NewListViewModel"
    }
}