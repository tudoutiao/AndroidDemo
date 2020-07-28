package com.gozap.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * Create by liuxue on 2020/7/27 0027.
 * description:
 */
open class BaseActivity : AppCompatActivity(), LifecycleOwner {
    var mLifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }
}