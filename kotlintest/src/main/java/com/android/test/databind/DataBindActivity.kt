package com.android.test.databind

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.test.R
import com.android.test.databinding.ActivityDataBindBinding

/**
 * https://blog.csdn.net/qinbin2015/article/details/93916842
 *
 * 单项绑定
 * android:text="@{user.name}"
 * 双向绑定
 * android:text="@={user.name}"
 */
class DataBindActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityDataBindBinding
    lateinit var user: ObservableUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_data_bind
        )
        user = ObservableUser()
        user.name.set("lilei")
        user.pwd.set("0000")
        mBinding.user = user

        Handler().postDelayed(Runnable {
//            user.name.set("hanmeimei")
//            user.pwd.set("0000")
        }, 1000)
    }
}


