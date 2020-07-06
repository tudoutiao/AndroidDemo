package com.android.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class LazyActivity : AppCompatActivity() {

    val TAG: String by lazy {
        this::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lazy)


        //===============
        val site = Site(
            // 构造函数接受一个映射参数
            mapOf(
                "name" to "菜鸟教程",
                "url" to "www.runoob.com"
            )
        )
        // 读取映射值
        println(site.name)
        println(site.url)

        var map:MutableMap<String, Any?> = mutableMapOf(
            "name" to "菜鸟教程",
            "url" to "www.runoob.com"
        )

        val siteWithVar = SiteWithVar(map)

        println(siteWithVar.name)
        println(siteWithVar.url)

        println("--------------")
        map.put("name", "Google")
        map.put("url", "www.google.com")

        println(siteWithVar.name)
        println(siteWithVar.url)

        //===============
        var user: User = User()
        user.name = "第一次赋值"
        user.name = "第二次赋值"
        //================
        val b = BaseImpl(10)
        Deviced(b).print()
        //===============
        val e = Example()
        println("" + e.p)     // 访问该属性，调用 getValue() 函数

        e.p = "Runoob"   // 调用 setValue() 函数
        println("" + e.p)
    }

    //------------------把属性存储在映射中
    class Site(val map: Map<String, Any?>) {
        val name: String by map
        val url: String by map
    }

    class SiteWithVar(val map: MutableMap<String, Any?>) {
        val name: String by map
        val url: String by map
    }
    //-----------------可观察属性Observable
    //observable 可以用于实现观察者模式。
    //Delegates.observable() 函数接受两个参数: 第一个是初始化值, 第二个是属性值变化事件的响应器(handler)。
    //在属性赋值后会执行事件的响应器(handler)，它有三个参数：被赋值的属性、旧值和新值：
    class User {
        var name: String by Delegates.observable("初始值") { property, oldValue, newValue ->
            println("old:$oldValue -> new :$newValue")
        }
    }

    //----------------类委托
    //类的委托即一个类中定义的方法实际是调用另一个类的对象的方法来实现的。
    //
    //以下实例中派生类 Derived 继承了接口 Base 所有方法，并且委托一个传入的 Base 类的对象来执行这些方法。
    //
    //在 Derived 声明中，by 子句表示，将 b 保存在 Derived 的对象实例内部，而且编译器将会生成继承自 Base 接口的所有方法, 并将调用转发给 b。
    interface Base {
        fun print()
    }

    class BaseImpl(val x: Int) : Base {
        override fun print() {
            Log.e("-----", "" + x)
        }
    }

    class Deviced(b: Base) : Base by b


    //------------------属性委托
    //属性委托指的是一个类的某个属性值不是在类中直接进行定义，而是将其托付给一个代理类，从而实现对该类的属性统一管理。
    //
    //val/var <属性名>: <属性的数据类型> by <表达式：委托代理类>
    //
    //
    class Example {
        var p: String by Delegate()
    }


    class Delegate {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return "$thisRef, 这里委托了 ${property.name} 属性"
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            println("$thisRef 的 ${property.name} 属性赋值为 $value")
        }
    }
}
