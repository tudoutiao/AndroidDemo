package com.gozap.basetest.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gozap.basetest.R

class RxJavaMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java_main)
        //多个观察者
        var observer1=ObserverImpl();
        var observer2=ObserverImpl();
        var observer3=ObserverImpl();
        //一个被观察者
        var observable=ObservableImpl()

        //注册
        observable.registerObserver(observer1)
        observable.registerObserver(observer2)
        observable.registerObserver(observer3)

        //产生事件
        observable.changeObserver()

        //对比
        //标准的观察者设计模式：多个观察者，一个被观察者，注册多次，需要主动产生事件
        //rxjava中的变种模式，一个观察者，多个被观察者，只订阅一次（一旦注册，马上分发事件）
    }
}