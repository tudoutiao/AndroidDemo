package com.gozap.basetest.rxjava

/**
 * Create by liuxue on 2020/10/19 0019.
 * description: 被观察者
 */
interface Observable {
    //注册 观察者
    fun registerObserver(observer: Observer)

    //移除 观察者
    fun unregisterObserver(observer: Observer)

    //产生事件
    fun changeObserver()
}