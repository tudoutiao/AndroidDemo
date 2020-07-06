package com.android.test.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.test.bean.User

/**
 * Create by liuxue on 2020/5/9 0009.
 * description:
 */
class SharedViewModel :ViewModel(){
    val selected = MutableLiveData<User>()

    fun select(item: User) {
        selected.value = item
    }
}