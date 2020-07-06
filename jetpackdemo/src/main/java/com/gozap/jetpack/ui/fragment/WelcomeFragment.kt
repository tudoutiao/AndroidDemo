package com.gozap.jetpack.ui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.gozap.jetpack.R
import com.gozap.jetpack.ui.common.BaseConstant
import com.gozap.jetpack.ui.util.AppPrefsUtils
import kotlinx.android.synthetic.main.fragment_welcome.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class WelcomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.setOnClickListener {
            //设置动画参数
            val navOption = navOptions {
                anim {
                    enter = R.anim.common_slide_in_right
                    exit = R.anim.common_slide_out_left
                    popEnter = R.anim.common_slide_in_left
                    popExit = R.anim.common_slide_out_right
                }
            }

            // ===========
            val name = AppPrefsUtils.getString(BaseConstant.SP_USER_NAME)
            val bundle = Bundle()
            bundle.putString(BaseConstant.ARGS_NAME, name)
            findNavController().navigate(
                R.id.action_welcome_to_login,
                bundle,
                navOption
            )

        }
        btn_register.setOnClickListener {
//            it.findNavController().navigate(R.id.registerFragment) 使用destination Id  navOptions 动画无效
//            it.findNavController().navigate(R.id.action_welcomeFragment_to_registerFragment)
            Navigation.createNavigateOnClickListener(R.id.action_welcome_to_register, null)
            // =====Safe Args
//            val action = WelcomeFragmentDirections.actionWelcomeToRegister("TeaOf1995@Gamil.com")
//            findNavController().navigate(action)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
