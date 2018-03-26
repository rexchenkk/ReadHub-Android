package com.rexonchen.readhub.utils.network

import android.content.Context
import com.rexonchen.readhub.model.entity.NewsList
import okhttp3.Cache
import okhttp4k.OkhttpBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.io.File
import com.rexonchen.readhub.utils.cache.CacheManager

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
    lateinit var apiService: ApiService

    fun init(context:Context,debug:Boolean=false){
        val client = OkhttpBuilder().build {
            connectTimeout = TIME_OUT_CONNECTION
            readTimeout = TIME_OUT_READ
            writeTimeout = TIME_OUT_WRITE
            cache {
                CacheManager.getInstance().init(context, CACHE_SIZE_DISK, CACHE_SIZE_MEMORY, File(context.applicationContext.cacheDir, "response").absolutePath)
                val httpCacheDirectory = File(context.applicationContext.cacheDir, "responses")
                Cache(httpCacheDirectory, CACHE_SIZE_DISK)
            }
        }

        apiService = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService::class.java)
    }
}


interface ApiService {

    @GET("news")
    fun news(@Query("lastCursor") lastCursor: Long = System.currentTimeMillis(),
             @Query("pageSize") pageSize: Int = 10): Call<NewsList>

}