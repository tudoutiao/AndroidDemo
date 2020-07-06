package com.gozap.jetpack.ui.db.respository

import androidx.lifecycle.LiveData
import com.gozap.jetpack.ui.db.dao.FavouriteShoeDao
import com.gozap.jetpack.ui.db.data.FavouriteShoe
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.util.*

class FavouriteShoeRepository private constructor(private val favouriteShoeDao: FavouriteShoeDao) {

    /**
     * 查看某个用户是否有喜欢记录
     */
    fun findFavouriteShoe(userId: Long, shoeId: Long): LiveData<FavouriteShoe?> =
        favouriteShoeDao.findFavouriteShoeByUserIdAndShoeId(userId, shoeId)

    /**
     * 收藏一双鞋
     */
    suspend fun createFavouriteShoe(userId: Long, shoeId: Long) {
        withContext(IO) {
            favouriteShoeDao.insertFavouriteShoe(
                FavouriteShoe(
                    shoeId,
                    userId,
                    Calendar.getInstance()
                )
            )
        }
    }

    companion object {
        @Volatile
        private var instance: FavouriteShoeRepository? = null

        fun getInstance(favouriteShoeDao: FavouriteShoeDao): FavouriteShoeRepository =
            instance ?: synchronized(this) {
                instance
                    ?: FavouriteShoeRepository(favouriteShoeDao).also {
                        instance = it
                    }
            }

    }
}