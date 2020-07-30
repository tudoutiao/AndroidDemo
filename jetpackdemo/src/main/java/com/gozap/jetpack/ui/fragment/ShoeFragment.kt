package com.gozap.jetpack.ui.ui.fragment

import android.animation.AnimatorSet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gozap.jetpack.databinding.FragmentShoeBinding
import com.gozap.jetpack.ui.ui.adapter.ShoeAdapter
import com.gozap.jetpack.ui.viewmodel.ShoeModel
import com.gozap.jetpack.viewmodel.CustomViewModelProvider

/**
 * 鞋子页面
 */
class ShoeFragment : Fragment() {

    val TAG: String by lazy {
        ShoeFragment::class.java.simpleName
    }


    // 动画集合，用来控制动画的有序播放
    private var animatorSet: AnimatorSet? = null

    // 圆的半径
    private var radius: Int = 0

    // FloatingActionButton宽度和高度，宽高一样
    private var width: Int = 0

     val viewModel: ShoeModel by viewModels {
        CustomViewModelProvider.providerShoeModel(requireContext())
    }

//    val viewModel: ShoeModel by lazy {
//        ViewModelProviders.of(this).get(ShoeModel::class.java) //没有构造参数时
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeBinding = FragmentShoeBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = ShoeAdapter(context!!)
        binding.recycler.adapter = adapter
        onSubscribeUi(adapter, binding)
        return binding.root
    }

    /**
     * 鞋子数据更新的通知
     */
    private fun onSubscribeUi(adapter: ShoeAdapter, binding: FragmentShoeBinding) {
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.submitList(it)
            }
        })
    }

}
