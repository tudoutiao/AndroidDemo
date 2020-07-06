package com.android.test.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.test.R
import com.android.test.base.BaseFragment
import com.android.test.base.ListFragmentAdapter
import kotlinx.android.synthetic.main.activity_scroll.*

/**
 * 向下滚动，标题和tab隐藏
 * 向上滑动tab显示（无需顶部），tab显示，只有在滚动到顶部 title才显示
 */
class ScrollActivity : AppCompatActivity() {

    var arrayList: ArrayList<BaseFragment> = ArrayList()

    val titleList: ArrayList<String> by lazy {
        arrayListOf("首页", "话题", "我的")
    }

    lateinit var listFragmentAdapter: ListFragmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)

        arrayList = arrayListOf(
            BaseFragment.newInstance("首页", ""),
            BaseFragment.newInstance("话题", ""),
            BaseFragment.newInstance("我的", "")
        )



        listFragmentAdapter = ListFragmentAdapter(supportFragmentManager, arrayList, titleList)
        viewPager.adapter = listFragmentAdapter
        viewPager.setCurrentItem(0)
        tab_layout.setupWithViewPager(viewPager)

    }
}
