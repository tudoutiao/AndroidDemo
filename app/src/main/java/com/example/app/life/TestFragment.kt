package com.example.app.life

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app.R
import kotlinx.android.synthetic.main.fragment_test.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TestFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.e("life", "onCreate-$param2")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("life", "onCreateView-$param2")
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("life", "onViewCreated-$param2")
        content.text = param1
    }

    override fun onStart() {
        super.onStart()
        Log.e("life", "onStart-$param2")
    }

    override fun onResume() {
        super.onResume()
        Log.e("life", "onResume-$param2")
    }

    override fun onPause() {
        super.onPause()
        Log.e("life", "onPause-$param2")
    }

    override fun onStop() {
        super.onStop()
        Log.e("life", "onStop-$param2")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("life", "onDestroy-$param2")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("life", "onDestroyView-$param2")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("life", "onDetach-$param2")
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e("life", "setUserVisibleHint---$param2---$isVisibleToUser")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e("life", "onHiddenChanged---$param2----$hidden")
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}