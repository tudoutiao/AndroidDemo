package com.example.app.life

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Create by liuxue on 2021/1/25 0025.
 * description:
 */
class ListFragmentAdapter(
    fm: FragmentManager,
    val fragmentLists: MutableList<Fragment>,
    val tabNameList: MutableList<String>
) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragmentLists.get(position)
    }

    override fun getCount(): Int {
        return fragmentLists?.let {
            it.size
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNameList?.let {
            it.get(position)
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        var view: View = `object` as View
        return view.getTag() as Int
    }

}
