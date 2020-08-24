package com.gozap.mine.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gozap.mine.databinding.FragmentGanHuoBinding

private const val ARG_PARAM1 = "index"
private const val ARG_PARAM2 = "title"

class GanHuoFragment : BaseFragment() {
    private var param1: Int? = null
    private var param2: String? = null
    lateinit var binding: FragmentGanHuoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGanHuoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey("index") }?.apply {
//            binding.text1.text = getInt("index").toString()
        }
    }

}