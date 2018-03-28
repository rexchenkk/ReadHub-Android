package com.rexonchen.readhub.view

import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rexonchen.readhub.R
import com.rexonchen.readhub.model.entity.News
import com.rexonchen.readhub.utils.autoNotify
import kotlinx.android.synthetic.main.news_item.view.*
import kotlin.properties.Delegates

/**
 * Created by rexonchen on 2018/3/26.
 */
class NewsAdapter(private val fragmentManager:FragmentManager): RecyclerView.Adapter<NewsViewHolder>(){
    var newsList: List<News> by Delegates.observable(emptyList()) { prop, old, new ->
        autoNotify(old, new) { o, n -> o.id == n.id }
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position]){news ->
            news_title.text=news.title
            news_time.text=news.publishDate
            setOnClickListener {
                fragmentManager.show(news)
            }
        }
    }

    override fun getItemCount(): Int =newsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder=
            NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false))

    private fun FragmentManager.show(news: News) {
        val newsFragment = NewsFragment.forNews(news.id)
        this.beginTransaction()
                .addToBackStack("news")
                .replace(R.id.fragment_container,
                        newsFragment, null).commit()
    }

}