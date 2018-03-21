package com.rexonchen.readhub.utils.network

import android.content.Context
import com.rexonchen.readhub.model.entity.NewsList
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by rexonchen on 2018/3/21.
 */
const val TIME_OUT_READ = 5000L
const val TIME_OUT_CONNECTION = 5000L
const val TIME_OUT_WRITE = 5000L

const val CACHE_SIZE_DISK = (1024 * 1024 * 5).toLong()
const val CACHE_SIZE_MEMORY = 1024 * 1024 * 5

const val BASE_URL = "https://api.readhub.me/"

object ReadHubApi{
    fun init(context:Context,debug:Boolean=false){

    }
}


interface ApiService {

    @GET("news")
    fun news(@Query("lastCursor") lastCursor: Long = System.currentTimeMillis(),
             @Query("pageSize") pageSize: Int = 10): Call<NewsList>

    @GET("techNews")
    fun techNews(@Query("lastCursor") lastCursor: Long = System.currentTimeMillis(),
                 @Query("pageSize") pageSize: Int = 10): Call<NewsList>

    @GET("blockchain")
    fun blockchain(@Query("lastCursor") lastCursor: Long = System.currentTimeMillis(),
                   @Query("pageSize") pageSize: Int = 10): Call<NewsList>

}