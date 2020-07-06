package com.android.test.view

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.test.R
import com.android.test.base.MyAdatper
import kotlinx.android.synthetic.main.activity_scroll2.*

class ScrollActivity2 : AppCompatActivity() {
    var arrayList = ArrayList<String>()
    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll2)

        while (50 > index) {
            arrayList.add("current index is $index")
            index++
        }

        var adatper = MyAdatper(this, arrayList)
        var layoutManager: LinearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adatper
        swipeRefresh.setOnRefreshListener {
            Handler().postDelayed({
                swipeRefresh.isRefreshing = false
            }, 3000)
        }
    }
}
