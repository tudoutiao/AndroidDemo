package com.gozap.jetpack.ui.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gozap.jetpack.ui.db.data.User

/**
 * Create by liuxue on 2020/5/9 0009.
 * description:
 */

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE user_account = :account AND user_pwd = :pwd")
    fun login(account:String,pwd:String): LiveData<User?>

    @Query("SELECT * FROM user WHERE id=:id")
    fun findUserById(id:Long): LiveData<User>

    @Query("SELECT * FROM user")
    fun getAllUsers():List<User>


    @Insert
    fun insertUser(user:User):Long

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user:User)
}