package com.android.test

import android.app.Application
import android.util.Log
import com.android.test.dragger.AppComponent
import com.android.test.dragger.DaggerAppComponent

/**
 * Create by liuxue on 2020/4/30 0030.
 * description:
 */
class MyApp : Application() {


    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Log.e("------", "app:" + appComponent.getAppApi())
    }


}