package com.android.test.livedata

import android.content.Context

/**
 * Create by liuxue on 2020/8/3 0003.
 * description:
 */

object CustomViewModelProvider {
    fun providerMyModel(context: Context):MyModelFactory{
        return MyModelFactory("MyModel")
    }
}