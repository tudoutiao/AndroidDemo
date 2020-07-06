package com.gozap.jetpack.db

import android.content.Context
import com.gozap.jetpack.ui.db.AppDataBase
import com.gozap.jetpack.ui.db.respository.FavouriteShoeRepository
import com.gozap.jetpack.ui.db.respository.ShoeRepository
import com.gozap.jetpack.ui.db.respository.UserRepository

/**
 * Create by liuxue on 2020/5/12 0012.
 * description:
 */
object RepositoryProvider {

    /**
     * 得到用户仓库
     */
    fun providerUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance(AppDataBase.getInstance(context).userDao())
    }

    /**
     * 得到鞋的本地仓库
     */
    fun providerShoeRepository(context: Context): ShoeRepository {
        return ShoeRepository.getInstance(AppDataBase.getInstance(context).shoeDao())
    }

    /**
     * 得到收藏记录的仓库
     */
    fun providerFavouriteShoeRepository(context: Context): FavouriteShoeRepository {
        return FavouriteShoeRepository.getInstance(
            AppDataBase.getInstance(context).favouriteShoeDao()
        )
    }


}