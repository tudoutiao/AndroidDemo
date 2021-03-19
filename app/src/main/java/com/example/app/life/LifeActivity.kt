package com.example.app.life

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.app.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_life.*

class LifeActivity : AppCompatActivity() {
    lateinit var fragmentAdatpter: ListFragmentAdapter;
    var titleList: MutableList<String> = mutableListOf("发布", "回复", "删除")
    var fragmentList: MutableList<Fragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life)
//        initViewPagerTest()

        initFrameTest()
    }

    private fun initFrameTest() {
        for (i in titleList.indices) {
            var fragment = supportFragmentManager.findFragmentByTag("fragment$i")
            if (null == fragment) {
                fragment = TestFragment.newInstance("fragment$i", "$i")
            }
            fragmentList.add(i, fragment)
        }

        btn1.setOnClickListener {
            changeFragment(0)
        }

        btn2.setOnClickListener {
            changeFragment(1)
        }

        btn3.setOnClickListener { changeFragment(2) }
    }

    fun changeFragment(index: Int) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(R.id.fragment, fragmentList.get(index), "fragment$index")
        for (i in titleList.indices) {
            if (index != i) {
                transaction.hide(fragmentList.get(index))
            }
        }
        transaction.show(fragmentList.get(index))
        transaction.commitAllowingStateLoss()
    }


    private fun initViewPagerTest() {
        for (i in titleList.indices) {
            var fragment = supportFragmentManager.findFragmentByTag("fragment$i")
            if (null == fragment) {
                fragment = TestFragment.newInstance("fragment$i", "$i")
            }
            fragmentList.add(i, fragment)
        }

        fragmentAdatpter = ListFragmentAdapter(
            supportFragmentManager,
            fragmentList,
            titleList
        )
        view_pager.adapter = fragmentAdatpter
        tab_layout.setupWithViewPager(view_pager)
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
            }
        })

        tab_layout.addOnTabSelectedListener(onTabListener)

    }

    var onTabListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
        }

    }


}