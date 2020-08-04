package com.gozap.jetpack.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gozap.jetpack.ui.db.respository.ShoeRepository
import com.gozap.jetpack.viewmodel.FavouriteModel

class FavouriteModelFactory(
    private val repository: ShoeRepository
    , private val userId: Long
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavouriteModel(repository, userId) as T
    }
}