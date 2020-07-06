package com.android.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LazyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lazy)
    }
}
