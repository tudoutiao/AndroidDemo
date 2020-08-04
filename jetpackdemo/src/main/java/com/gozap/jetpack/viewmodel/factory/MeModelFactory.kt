package com.gozap.jetpack.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gozap.jetpack.ui.db.respository.UserRepository
import com.gozap.jetpack.viewmodel.MeModel

/**
 * Create by liuxue on 2020/8/4 0004.
 * description:
 */
class MeModelFactory(val userRepository: UserRepository) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  MeModel(userRepository) as T
    }
}