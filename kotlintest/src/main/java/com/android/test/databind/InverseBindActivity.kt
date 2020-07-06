package com.android.test.databind

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.test.R
import com.android.test.databinding.ActivityInverseBindBinding

class InverseBindActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityInverseBindBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_inverse_bind)
        //==============
//        binding.rating = 5f
//        Handler().postDelayed({
//            Toast.makeText(this@InverseBindActivity, ""+binding.rating, Toast.LENGTH_LONG)
//                .show()
//        }, 2000)

        //===============
        binding.rt = 3f
        Handler().postDelayed({
            Toast.makeText(this@InverseBindActivity, "" + binding.rt, Toast.LENGTH_LONG)
                .show()
        }, 2000)
    }
}
