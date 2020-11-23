package com.gozap.mine.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import com.gozap.mine.ui.observer.MyObserver

/**
 * Create by liuxue on 2020/7/27 0027.
 * description:
 */
open class BaseActivity : AppCompatActivity(), LifecycleObserver {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(MyObserver())
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initView()
        addListener()
    }

    private fun addListener() {
    }

    private fun initView() {
    }


    override fun onStart() {
        super.onStart()
        Log.e(javaClass.name, "-------onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(javaClass.name, "-------onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(javaClass.name, "-------onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(javaClass.name, "-------onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(javaClass.name, "-------onDestroy")
    }


}