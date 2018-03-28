package com.rexonchen.readhub.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import com.rexonchen.readhub.model.dao.NewsDao
//import com.rexonchen.readhub.model.dao.NewsListDao
import com.rexonchen.readhub.model.entity.News
import com.rexonchen.readhub.utils.network.ReadHubApi
import com.rexonchen.readhub.utils.network.enqueue
//import com.rexonchen.readhub.model.entity.NewsList
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.lazyDefer

/**
 * Created by rexonchen on 2018/3/21.
 */
@Database(entities = [News::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    private val mIsDatabaseCreated = MutableLiveData<Boolean>()
    fun getDatabaseCreated(): LiveData<Boolean> = mIsDatabaseCreated
    abstract fun newsDao():NewsDao
//    abstract fun newsListDao():NewsListDao

    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }



    companion object {
        @Volatile
        private var sInstance:AppDatabase?=null
        val DATABASE_NAME="read-hub"

        fun getInstance(context:Context):AppDatabase=
                sInstance?:Unit.let {
                    synchronized(AppDatabase::class.java){
                        sInstance?: buildDatabase(context.applicationContext).apply {
                            updateDatabaseCreated(context.applicationContext)
                        }
                    }
                }

        private fun buildDatabase(appContext:Context):AppDatabase{
            return Room.databaseBuilder(appContext,AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object :RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            async {
                                delay(4000)
                                //TODO
                                val newsList=DataGenerator.generateNewsList()
                                val news=DataGenerator.generateNews()

                                AppDatabase.getInstance(appContext).let{
                                    it.runInTransaction {

                                        news.forEach{x->it.newsDao().insertNews(x)}
//                                        it.newsDao().insertNews(news)
//                                        it.newsListDao().insertNewsList(newsList)
                                    }
                                    it.setDatabaseCreated()
                                }
                            }
                        }
                    }).build()
        }

    }
}