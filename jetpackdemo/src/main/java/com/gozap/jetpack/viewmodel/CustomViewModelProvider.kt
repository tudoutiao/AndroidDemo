package com.gozap.jetpack.viewmodel

import android.content.Context
import com.gozap.jetpack.db.RepositoryProvider
import com.gozap.jetpack.ui.db.respository.ShoeRepository

/**
 * Create by liuxue on 2020/7/30 0030.
 * description:
 */

object CustomViewModelProvider {
    fun providerShoeModel(context: Context): ShoeModelFactory {
        val repository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        return ShoeModelFactory(repository)
    }
}