package com.gozap.jetpack.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gozap.jetpack.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * https://www.jianshu.com/p/66b93df4b7a6
 */


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = host.navController

        navigation_view.setupWithNavController(navController)
    }


}
