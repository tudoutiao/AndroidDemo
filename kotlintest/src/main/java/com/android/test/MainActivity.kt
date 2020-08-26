package com.android.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.test.databind.DataBindActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.dataBind).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v!!.id
        when (id) {
            R.id.dataBind -> {
                startActivity(Intent(this, DataBindActivity::class.java))
            }
        }
    }




}

