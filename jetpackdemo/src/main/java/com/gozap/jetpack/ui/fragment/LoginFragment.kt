package com.gozap.jetpack.ui.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.gozap.jetpack.R
import com.gozap.jetpack.databinding.FragmentLoginBinding
import com.gozap.jetpack.ui.MainActivity
import com.gozap.jetpack.ui.common.BaseConstant
import com.gozap.jetpack.ui.util.AppPrefsUtils
import com.gozap.jetpack.ui.viewmodel.LoginModel
import com.gozap.jetpack.viewmodel.CustomViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    val loginModel: LoginModel by viewModels {
        CustomViewModelProvider.providerLoginModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //接收bundle传递参数
        arguments
        var dataBinding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        onSubscribeUi(dataBinding)

        // 判断当前是否是第一次登陆
        val isFirstLaunch = AppPrefsUtils.getBoolean(BaseConstant.IS_FIRST_LAUNCH)
        if (isFirstLaunch) {
            onFirstLaunch()
        }

        return dataBinding.root
    }


    private fun onSubscribeUi(binding: FragmentLoginBinding) {
        binding.model = loginModel
        binding.activity = activity
        // 如果使用LiveData下面这句必须加上 ！！！????
        binding.lifecycleOwner = this

        binding.btnLogin.setOnClickListener {
            loginModel.login()?.observe(LoginFragment@ this, Observer { user ->
                user?.let {
                    AppPrefsUtils.putLong(BaseConstant.SP_USER_ID, it.id)
                    AppPrefsUtils.putString(BaseConstant.SP_USER_NAME, it.account)
                    val intent = Intent(context, MainActivity::class.java)
                    context!!.startActivity(intent)
                    Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    // 第一次启动的时候调用
    private fun onFirstLaunch() {
        loginModel.viewModelScope.launch(Dispatchers.Main) {
            val str = withContext(Dispatchers.IO) {
                loginModel.onFirstLaunch()
            }
            Toast.makeText(context!!, str, Toast.LENGTH_SHORT).show()
            AppPrefsUtils.putBoolean(BaseConstant.IS_FIRST_LAUNCH, false)
        }
    }

}
