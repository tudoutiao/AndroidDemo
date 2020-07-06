package com.android.test.dragger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.test.MyApp
import com.android.test.R
import com.android.test.bean.City
import com.android.test.bean.User
import kotlinx.android.synthetic.main.activity_dagger.*
import javax.inject.Inject
import javax.inject.Named

class DaggerActivity : AppCompatActivity() {
    @Named("a")
    @Inject
    lateinit var user: User

    @Named("b")
    @Inject
    lateinit var user2: User

    @Inject
    lateinit var city1: City

    @Inject
    lateinit var city2: City


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dagger)
        /***
         * 第一步 添加依赖关系
         */
//        DaggerMainComponent.builder().build().inject(this@DaggerActivity)

        //dependence其他component
        DaggerMainComponent.builder().appComponent(DaggerAppComponent.create())
            .build()
            .inject(this@DaggerActivity)

        /***
         * 第三步  调用A 对象的方法
         */
        var info: String = user.toString() + "\n " + user2.toString()
        tv_name.text = info

        //singleton
        Log.e("--------city1", "" + city1)
        Log.e("--------city2", "" + city2)
        Log.e("--------activity1-app", "" + MyApp.instance.appComponent.getAppApi())
        //
        dagger.setOnClickListener({
            startActivity(Intent(this, Dagger2Activity::class.java))
        })

    }
}
