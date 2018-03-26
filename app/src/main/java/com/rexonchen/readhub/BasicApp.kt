package com.rexonchen.readhub

import android.app.Application
import com.rexonchen.readhub.model.AppDatabase
import com.rexonchen.readhub.model.NewsRepository

/**
 * Created by rexonchen on 2018/3/26.
 */
class BasicApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }

    private fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this)
    }

    fun getRepository(): NewsRepository {
        return NewsRepository.getInstance(getDatabase())
    }
}