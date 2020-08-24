package com.gozap.mine.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Create by liuxue on 2020/7/30 0030.
 * description:
 */
open class BaseFragment : Fragment(), LifecycleObserver {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)

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