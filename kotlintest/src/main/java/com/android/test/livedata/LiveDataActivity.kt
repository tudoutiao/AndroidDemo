package com.android.test.livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.test.R
import com.android.test.bean.User
import com.android.test.databinding.ActivityLiveDataBinding
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {

//    val model: MyViewModel by viewModels()

    private val viewModel2: MyViewModel by viewModels {
        CustomViewModelProvider.providerMyModel(this)
    }
    lateinit var binding: ActivityLiveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_data)

        val navController = findNavController(R.id.my_nav_host_fragment)
        navigation_view.setupWithNavController(navController)

        var model = ViewModelProviders.of(this)[MyViewModel::class.java]
        // Create the observer which updates the UI.
        val nameObserver = Observer<User> {
            // Update the UI, in this case, a TextView.
            tvname.text = it.name + "---" + model.userName.value
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getUsers().observe(this, nameObserver)


        button.setOnClickListener {
            var user = User()
            user.name = "456"
            user.age = "456"
            model.user.value = user
        }
    }

}
