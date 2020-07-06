package com.gozap.jetpack.ui.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gozap.jetpack.ui.db.dao.FavouriteShoeDao
import com.gozap.jetpack.ui.db.dao.ShoeDao
import com.gozap.jetpack.ui.db.dao.UserDao
import com.gozap.jetpack.ui.db.data.FavouriteShoe
import com.gozap.jetpack.ui.db.data.Shoe
import com.gozap.jetpack.ui.db.data.User

/**
 * Create by liuxue on 2020/5/11 0011.
 * description:
 */

@Database(
    entities = [User::class, Shoe::class, FavouriteShoe::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    // 得到UserDao
    abstract fun userDao(): UserDao

    // 得到ShoeDao
    abstract fun shoeDao(): ShoeDao

    // 得到FavouriteShoeDao
    abstract fun favouriteShoeDao(): FavouriteShoeDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context)
                    .also {
                        instance = it
                    }
            }
        }

        private fun buildDataBase(context: Context): AppDataBase {
            return Room
                .databaseBuilder(context, AppDataBase::class.java, "jetPackDemo-database")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        // 读取鞋的集合
                        /*val request = OneTimeWorkRequestBuilder<ShoeWorker>().build()
                        WorkManager.getInstance().enqueue(request)*/
                    }
                })
                .build()
        }

    }

}