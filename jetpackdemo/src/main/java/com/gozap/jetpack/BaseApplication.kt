package com.gozap.jetpack.ui

import android.app.Application
import android.content.Context

/**
 * Create by liuxue on 2020/5/7 0007.
 * description:
 */

open class BaseApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}