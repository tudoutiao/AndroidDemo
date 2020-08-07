package com.gozap.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gozap.jetpack.ui.db.respository.FavouriteShoeRepository
import com.gozap.jetpack.ui.db.respository.ShoeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Create by liuxue on 2020/8/7 0007.
 * description:
 */

class DetailModel(
    val shoeRepository: ShoeRepository,
    val favRepository: FavouriteShoeRepository,
    val shoeId: Long,
    val userId: Long
) : ViewModel() {


    val shoe = shoeRepository.getShoeById(shoeId)

    val favouriteShoe = favRepository.findFavouriteShoe(userId, shoeId)


    fun favourite(): String {
        viewModelScope.launch {
            favRepository.createFavouriteShoe(userId, shoeId)
        }
        return ""
    }


}