package com.gozap.basetest

import android.app.Application
import com.facebook.stetho.Stetho
import java.util.function.Consumer

/**
 * Create by liuxue on 2020/8/26 0026.
 * description:
 */
class MyApp : Application() {


    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

    }
}