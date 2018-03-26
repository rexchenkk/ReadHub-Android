package com.rexonchen.readhub.view

import android.support.v7.widget.RecyclerView
import android.view.View
/**
 * Created by rexonchen on 2018/3/26.
 */
class NewsViewHolder(itemView:View?):RecyclerView.ViewHolder(itemView) {
    fun <T>bind(item:T,onBind:View.(T)->Unit)=itemView.onBind(item)
}