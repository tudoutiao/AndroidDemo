package com.gozap.mine.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.gozap.mine.R
import com.gozap.mine.databinding.FragmentGankBinding
import com.gozap.mine.ui.adapter.FragmentAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * 干货
 */
class GankFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentGankBinding
    val mTitles by lazy {
        requireActivity().resources.getStringArray(R.array.index_tab)
    }
    lateinit var fragmentAdapter: FragmentAdapter

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
        binding = FragmentGankBinding.inflate(inflater, container, false)
        fragmentAdapter = FragmentAdapter(this, mTitles)
        binding.vpIndexContent.adapter = fragmentAdapter

        TabLayoutMediator(binding.tlIndexHead, binding.vpIndexContent) { tab, position ->
            tab.text = mTitles[position]
        }.attach()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}