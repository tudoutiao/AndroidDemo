package com.android.test.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.test.bean.User

/**
 * Create by liuxue on 2020/5/9 0009.
 * description:
 */
class MyViewModel : ViewModel() {

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<User> {
        return user
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.
    }
}