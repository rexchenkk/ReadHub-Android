package com.rexonchen.readhub.utils

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView

/**
 * Created by rexonchen on 2018/3/21.
 */
fun <T> RecyclerView.Adapter<*>.autoNotify(oldList: List<T>, newList: List<T>, compare: (T, T) -> Boolean) {
    DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                compare(oldList[oldItemPosition], newList[newItemPosition])

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                newList[newItemPosition] == oldList[oldItemPosition]

    }).dispatchUpdatesTo(this)
}