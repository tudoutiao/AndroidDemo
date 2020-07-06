package com.android.test.dragger

import androidx.databinding.ObservableField
import com.android.test.bean.City
import com.android.test.bean.User
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


/**
 * Create by liuxue on 2020/4/29 0029.
 * description:
 */
//实例化两个对象
@Module
class MainModule2 {
    @Named("a")
    @Provides
    fun providerUserDev(): User {
        return User("lilei", "11")
    }

    @Named("b")
    @Provides
    fun providerUserRelease(): User {
        return User("hanmeimei", "22")
    }

    @ActivityScoped
    @Provides
    fun providerCity(): City {
        return City()
    }

}

