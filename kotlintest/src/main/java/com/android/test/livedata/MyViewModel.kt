package com.android.test.livedata

import androidx.lifecycle.*
import com.android.test.bean.User

/**
 * Create by liuxue on 2020/5/9 0009.
 * description:
 */
class MyViewModel(val data:String) : ViewModel() {

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>().also {
            loadUsers()
        }
    }
    val userName: LiveData<String> = Transformations.map(user) {
            user -> "${user.name} ${user.name}"
    }

    fun getUsers(): LiveData<User> {
        return user
    }

    private fun loadUsers() {
        var user = User()
        user.name = "123"
        user.age = "123"
    }
}