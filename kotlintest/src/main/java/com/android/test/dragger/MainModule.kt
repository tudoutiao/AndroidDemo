package com.android.test.dragger

import com.android.test.bean.City
import com.android.test.bean.User
import com.android.test.databind.ObservableUser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Create by liuxue on 2020/4/29 0029.
 * description:
 */
//第一步 添加@Module 注解
@Module
class MainModule {
    //第二步 使用Provider 注解 实例化对象
    @Provides
    fun providerUser(): User {
        return User()
    }

    @Provides
    fun providerUserWithName(name: String): User {
        return User(name, "55")
    }


    @Provides
    fun providerObservableUser(): ObservableUser {
        var user = ObservableUser()
        user.name.set("lili")
        user.pwd.set("000")
        return user
    }

}

