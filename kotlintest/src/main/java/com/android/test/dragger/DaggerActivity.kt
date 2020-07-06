package com.android.test.dragger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.test.R
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_dragger.*
class DraggerActivity : AppCompatActivity() {
    @Inject
    lateinit var car:Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dragger)

    }
}
