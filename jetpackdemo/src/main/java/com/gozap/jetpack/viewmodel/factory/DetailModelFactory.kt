package com.gozap.jetpack.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gozap.jetpack.ui.db.respository.FavouriteShoeRepository
import com.gozap.jetpack.ui.db.respository.ShoeRepository
import com.gozap.jetpack.viewmodel.DetailModel

/**
 * Create by liuxue on 2020/8/7 0007.
 * description:
 */
class FavouriteShoeModelFactory(val shoeRepository: ShoeRepository,val favRepository: FavouriteShoeRepository,val shoeId:Long,val userId:Long) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailModel(shoeRepository,favRepository,shoeId,userId) as T
    }
}