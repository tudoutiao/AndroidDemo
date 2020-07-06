package com.android.test.livedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.test.R
import com.android.test.bean.User
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        val navController = findNavController(R.id.my_nav_host_fragment)
        navigation_view.setupWithNavController(navController)

//        val host: NavHostFragment =
//            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
//        val navController = host.navController
//        navigation_view.setupWithNavController(navController)


        var model = ViewModelProviders.of(this)[MyViewModel::class.java]
        model.getUsers().observe(this, Observer<User> {
            tvname.text = it.name
        })

        button.setOnClickListener {
            var user = User()
            user.name = "123"
            user.age = "123"
            model.user.value = user
        }
    }

}
