package com.example.app.recycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.app.R

class RecyclerActivity : AppCompatActivity() {
    var fragmentList = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        initData()
        initView()
    }

    fun initData() {
        var fragment1 = Test1Fragment.newInstance("", "")
        fragmentList.add(fragment1)
    }

    fun initView() {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment, fragmentList.get(0))
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.show(fragmentList.get(0))
        transaction.commitAllowingStateLoss()

    }
}