package com.android.test.dragger

import dagger.Component
import javax.inject.Singleton

/**
 * Create by liuxue on 2020/4/29 0029.
 * description:
 */
@Singleton
@Component(modules = [AppModule::class,MainModule::class])
interface AppComponent {
    fun getAppApi(): AppApi
}
