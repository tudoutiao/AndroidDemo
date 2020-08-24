package com.gozap.mine.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gozap.mine.R
import com.gozap.mine.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var navController = findNavController(R.id.my_nav_host_fragment)
        dataBinding.navigationView.setupWithNavController(navController);

    }
}
