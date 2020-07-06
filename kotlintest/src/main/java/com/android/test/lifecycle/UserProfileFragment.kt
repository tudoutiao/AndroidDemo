package com.android.test.lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.android.test.R

/**
 * Create by liuxue on 2020/4/28 0028.
 * description:
 */
class UserProfileFragment : Fragment() {

    private val viewModel: UserProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.user.observe(viewLifecycleOwner) {

        }

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

}