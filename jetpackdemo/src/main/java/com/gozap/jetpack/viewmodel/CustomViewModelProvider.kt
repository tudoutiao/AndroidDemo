package com.gozap.jetpack.viewmodel

import android.content.Context
import com.gozap.jetpack.db.RepositoryProvider
import com.gozap.jetpack.ui.common.BaseConstant
import com.gozap.jetpack.ui.db.respository.ShoeRepository
import com.gozap.jetpack.ui.db.respository.UserRepository
import com.gozap.jetpack.ui.util.AppPrefsUtils
import com.gozap.jetpack.viewmodel.factory.*

/**
 * Create by liuxue on 2020/7/30 0030.
 * description:
 */

object CustomViewModelProvider {

    fun providerRegisterModel(context: Context): RegisterModelFactory {
        val repository: UserRepository = RepositoryProvider.providerUserRepository(context)
        return RegisterModelFactory(repository)
    }


    fun providerLoginModel(context: Context): LoginModelFactory {
        val repository: UserRepository = RepositoryProvider.providerUserRepository(context)
        return LoginModelFactory(repository, context)
    }

    fun providerFavouriteModel(context: Context): FavouriteModelFactory {
        val repository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        val userId: Long = AppPrefsUtils.getLong(BaseConstant.SP_USER_ID)
        return FavouriteModelFactory(repository, userId)
    }


    fun providerShoeModel(context: Context): ShoeModelFactory {
        val repository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        return ShoeModelFactory(repository)
    }

    fun providerMeMedel(context: Context): MeModelFactory {
        val repository: UserRepository = RepositoryProvider.providerUserRepository(context)
        return MeModelFactory(repository)
    }

}