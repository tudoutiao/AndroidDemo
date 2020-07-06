package com.gozap.jetpack.ui.db.respository

import androidx.lifecycle.LiveData
import com.gozap.jetpack.ui.db.dao.UserDao
import com.gozap.jetpack.ui.db.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Create by liuxue on 2020/5/11 0011.
 * description: 用户处理仓库
 */

class UserRepository constructor(var userDao: UserDao) {

    /**
     * 获取所有的用户
     */
    fun getAllUsers() = userDao.getAllUsers()

    /**
     * 根据id选择用户
     */
    fun findUserById(id: Long): LiveData<User> = userDao.findUserById(id)

    /**
     * 登录用户
     */
    fun login(account: String, pwd: String): LiveData<User?> = userDao.login(account, pwd)

    /**
     * 更新用户
     */
    suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.updateUser(user)
        }
    }

    /**
     * 注册一个用户
     */
    suspend fun register(email: String, account: String, pwd: String): Long {
        return withContext(Dispatchers.IO) {
            userDao.insertUser(
                User(
                    account,
                    pwd,
                    email,
                    "https://raw.githubusercontent.com/mCyp/Photo/master/1560651318109.jpeg"
                )
            )
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository =
            instance ?: synchronized(this) {
                instance
                    ?: UserRepository(userDao).also {
                        instance = it
                    }
            }

    }
}