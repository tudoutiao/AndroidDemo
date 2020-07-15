package com.example.app.refresh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import com.example.app.util.StringUtil
import kotlinx.android.synthetic.main.activity_refresh.*
import kotlinx.android.synthetic.main.activity_refresh.view.*

class RefreshActivity : AppCompatActivity() {

    val linkAdapter by lazy {
        LinkAdatpter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh)
        linkAdapter.dataList = StringUtil.getLinks()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = linkAdapter
        refreshLayout.isLongRefreshEnable=true
//        refreshLayout.startRefresh()


    }
}
