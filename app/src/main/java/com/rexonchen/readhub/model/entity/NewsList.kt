package com.rexonchen.readhub.model.entity

import android.arch.persistence.room.Entity

/**
 * Created by rexonchen on 2018/3/21.
 */
data class NewsList(val data:List<News>,
                    val pageSize:Int,
                    val totalItems:Long,
                    val totalPages:Long)