package com.gozap.jetpack.ui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gozap.jetpack.databinding.FragmentMeBinding
import com.gozap.jetpack.viewmodel.CustomViewModelProvider
import com.gozap.jetpack.viewmodel.MeModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MeFragment : Fragment() {

    val viemodel: MeModel by viewModels {
        CustomViewModelProvider.providerMeMedel(requireContext())
    }
    lateinit var binding: FragmentMeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeBinding.inflate(inflater, container, false)
        return binding.root
    }

}
