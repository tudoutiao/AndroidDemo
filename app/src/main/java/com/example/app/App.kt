package com.example.app

import android.app.Application
import android.content.Context

/**
 * Create by liuxue on 2020/7/13 0013.
 * description:
 */
open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this
        context = this.applicationContext
    }

    companion object {
        var context: Context? = null
        var app: Application? = null
    }
}