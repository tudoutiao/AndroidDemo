package com.gozap.basetest.rxjava

import android.util.Log

/**
 * Create by liuxue on 2020/10/19 0019.
 * description:
 */
class ObserverImpl : Observer {
    var TAG: String = ObserverImpl::class.java.name

    override fun change(obj: Any?) {
        Log.e(TAG, "change :" + obj)
    }

}