package com.android.test.dragger

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Create by liuxue on 2020/4/30 0030.
 * description:
 */
@Module
class AppModule {
    @Singleton
    @Provides
    fun providerAppApi(): AppApi {
        return AppApi()
    }
}