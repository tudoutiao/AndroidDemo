package com.gozap.jetpack.ui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gozap.jetpack.R
import com.gozap.jetpack.databinding.FragmentRegisterBinding
import com.gozap.jetpack.ui.common.BaseConstant
import com.gozap.jetpack.viewmodel.CustomViewModelProvider
import com.gozap.jetpack.viewmodel.RegisterModel


class RegisterFragment : Fragment() {

    val registerModel: RegisterModel by viewModels {
        CustomViewModelProvider.providerRegisterModel(requireContext())
    }

    lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentRegisterBinding.inflate(inflater, container, false)
        initData()
        onSubscribeUi()
        return binding.root
    }

    private fun onSubscribeUi() {
        binding.btnRegister.setOnClickListener {
            registerModel.register()
            val bundle = Bundle()
            bundle.putString(BaseConstant.ARGS_NAME, registerModel.n.value)
            findNavController().navigate(R.id.loginFragment, bundle, null)
        }
    }

    private fun initData() {
        // SafeArgs的使用
        val safeArgs:RegisterFragmentArgs by navArgs()
        val email = safeArgs.EMAIL
        binding.model?.mail?.value = email

        binding.lifecycleOwner = this
        binding.model = registerModel
        binding.activity = activity

    }


}
