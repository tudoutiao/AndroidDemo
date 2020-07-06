package com.android.test.dragger

import com.android.test.bean.City
import com.android.test.bean.User
import dagger.Component
import dagger.Module
import dagger.Provides


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
    fun providerUser(name: String): User {
        return User(name, "55")
    }

    @Provides
    fun providerCity(): City {
        return City("河北")
    }
}




@Component(modules = [MainModule::class])
interface MainComponent {
    //第三步  写一个方法 绑定Activity /Fragment
    fun inject(activity: DaggerActivity?)
}
