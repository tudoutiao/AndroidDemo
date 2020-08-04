package com.gozap.jetpack.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gozap.jetpack.ui.db.respository.UserRepository
import com.gozap.jetpack.ui.viewmodel.LoginModel

class LoginModelFactory(
    private val repository: UserRepository
    , private val context: Context
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginModel(repository) as T
    }
}