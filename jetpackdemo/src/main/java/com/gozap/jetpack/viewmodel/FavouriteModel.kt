package com.gozap.jetpack.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.gozap.jetpack.ui.db.data.Shoe
import com.gozap.jetpack.ui.db.respository.ShoeRepository

class FavouriteModel constructor(shoeRepository: ShoeRepository, userId:Long) : ViewModel() {

    // 鞋子集合的观察类
    val shoes: LiveData<List<Shoe>> = shoeRepository.getShoesByUserId(userId)

}