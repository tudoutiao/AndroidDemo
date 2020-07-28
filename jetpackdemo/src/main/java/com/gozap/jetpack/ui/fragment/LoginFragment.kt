package com.gozap.jetpack.ui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gozap.jetpack.R
import com.gozap.jetpack.databinding.FragmentLoginBinding
import com.gozap.jetpack.ui.viewmodel.LoginModel

class LoginFragment : Fragment() {

    lateinit var loginModel: LoginModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //接收bundle传递参数
        arguments
        var dataBinding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        onSubscribeUi(dataBinding)
        return dataBinding.root
    }


    private fun onSubscribeUi(dataBinding: FragmentLoginBinding) {
        loginModel = LoginModel("TeaOf", "123456", context!!)
        dataBinding.model = loginModel
        dataBinding.activity = activity
    }

}
