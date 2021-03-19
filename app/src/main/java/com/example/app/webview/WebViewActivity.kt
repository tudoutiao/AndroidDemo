package com.example.app.webview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.app.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    var fragmentList: MutableList<Fragment> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        changeFragment()
    }


    fun changeFragment() {
        var jsFragment = JSFragment.newInstance("", "")

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fragment,jsFragment)
        beginTransaction.show(jsFragment)
        beginTransaction.commitAllowingStateLoss()
    }
}