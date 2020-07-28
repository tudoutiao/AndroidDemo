package com.gozap.jetpack.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gozap.jetpack.BaseActivity
import com.gozap.jetpack.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * https://www.jianshu.com/p/66b93df4b7a6
 */


class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment

        val navController = findNavController(R.id.my_nav_host_fragment)
        navigation_view.setupWithNavController(navController)
    }


}
