package com.android.test.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.android.test.bean.User

/**
 * Create by liuxue on 2020/4/28 0028.
 * description:
 */

class UserProfileViewModel( savedStateHandle: SavedStateHandle) : ViewModel() {
    val userId: String = savedStateHandle["uid"] ?:
    throw IllegalArgumentException("missing user id")
    val user: LiveData<User> = TODO()
}
