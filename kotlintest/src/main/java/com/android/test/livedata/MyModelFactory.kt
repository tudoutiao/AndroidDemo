package com.android.test.livedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Create by liuxue on 2020/8/3 0003.
 * description:
 */
class MyModelFactory (val data:String):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(data) as T
    }
}