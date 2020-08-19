package com.gozap.mine.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Create by liuxue on 2020/7/30 0030.
 * description:
 */
class FragmentAdapter(
    fragment: Fragment,
    var mTitles: Array<String>,
    var mFragments: Array<Fragment>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = mTitles.size

    override fun createFragment(position: Int): Fragment {
        return mFragments.get(position)
    }

}