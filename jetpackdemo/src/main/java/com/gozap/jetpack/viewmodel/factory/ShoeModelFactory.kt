package com.gozap.jetpack.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gozap.jetpack.ui.db.respository.ShoeRepository
import com.gozap.jetpack.ui.viewmodel.ShoeModel

/**
 * Create by liuxue on 2020/7/30 0030.
 * description:
 */

class ShoeModelFactory(val respository: ShoeRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoeModel(respository) as T
    }
}