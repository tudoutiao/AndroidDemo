package com.android.test.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.test.R
import kotlinx.android.synthetic.main.activity_scroll2.*

class ScrollActivity : AppCompatActivity() {
    var arrayList = ArrayList<String>()
    var size: Int = 50
    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll2)

        while (size > index) {
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

        }
    }
}
