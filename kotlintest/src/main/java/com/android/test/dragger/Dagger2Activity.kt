package com.android.test.dragger

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.test.MyApp
import com.android.test.R
import com.android.test.bean.City
import com.android.test.databind.ObservableUser
import com.android.test.databinding.ActivityDragger2Binding
import javax.inject.Inject

class Dagger2Activity : AppCompatActivity() {

    lateinit var mBinding: ActivityDragger2Binding

    @Inject
    lateinit var user: ObservableUser

    @Inject
    lateinit var city: City

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dragger2)
//        DaggerMainComponent.builder().build().inject(this@Dagger2Activity)
        //
        DaggerMainComponent.builder().appComponent(DaggerAppComponent.create())
            .build()
            .inject(this@Dagger2Activity)

        mBinding.user = user
        Log.e("--------", user.name.get() + "--" + user.pwd.get())
        Log.e("--------", "--city:" + city)

        Log.e("--------activity2-app", "" + MyApp.instance.appComponent.getAppApi())

    }
}
