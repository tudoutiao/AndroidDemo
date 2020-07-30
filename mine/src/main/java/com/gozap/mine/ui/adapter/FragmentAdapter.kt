package com.gozap.mine.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gozap.mine.ui.fragment.GanHuoFragment

/**
 * Create by liuxue on 2020/7/30 0030.
 * description:
 */
class FragmentAdapter(
    fragment: Fragment,
    var mTitles: Array<String>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = mTitles.size

    override fun createFragment(position: Int): Fragment {
        val fragment = GanHuoFragment()
        fragment.arguments = Bundle().apply {
            putInt("object", position)
        }
        return fragment
    }

}