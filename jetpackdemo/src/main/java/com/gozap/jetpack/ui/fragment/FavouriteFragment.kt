package com.gozap.jetpack.ui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.gozap.jetpack.databinding.FragmentMarketBinding
import com.gozap.jetpack.ui.adapter.FavouriteAdapter
import com.gozap.jetpack.viewmodel.CustomViewModelProvider
import com.gozap.jetpack.viewmodel.FavouriteModel


class FavouriteFragment : Fragment() {

    val viewModel: FavouriteModel by viewModels {
        CustomViewModelProvider.providerFavouriteModel(requireContext())
    }

    lateinit var binding: FragmentMarketBinding
    lateinit var adapter: FavouriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarketBinding.inflate(inflater, container, false)
        context ?: return binding.root
        adapter = FavouriteAdapter(requireContext())
        binding.recycler.adapter = adapter
        onSubscribeUi()
        return binding.root
    }

    fun onSubscribeUi() {
        binding.empty.bind(arrayOf(binding.recycler))
        binding.empty.triggerLoading()
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            if (it != null && it.isNotEmpty()) {
                adapter.submitList(it)
            }
            binding.empty.triggerOkOrEmpty(adapter.itemCount > 0)
        })
    }

}
