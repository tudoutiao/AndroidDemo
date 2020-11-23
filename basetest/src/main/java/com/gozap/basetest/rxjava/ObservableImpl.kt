package com.gozap.basetest.rxjava

/**
 * Create by liuxue on 2020/10/19 0019.
 * description: 被观察者的实现类
 */
internal class ObservableImpl : Observable {
    //存储观察者集合
    var observers: MutableList<Observer> = mutableListOf()

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun unregisterObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun changeObserver() {
        observers.forEach {
            it.change("我发生改变了")
        }
    }
}