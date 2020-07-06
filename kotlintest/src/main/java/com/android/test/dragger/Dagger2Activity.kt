package com.android.test.dragger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.test.R
import com.android.test.databinding.ActivityDragger2Binding

class Dragger2Activity : AppCompatActivity() {

    lateinit var mBinding: ActivityDragger2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dragger2)

    }
}
