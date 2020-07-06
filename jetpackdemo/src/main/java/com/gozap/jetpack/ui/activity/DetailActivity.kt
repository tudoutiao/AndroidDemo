package com.gozap.jetpack.ui.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gozap.jetpack.R
import com.gozap.jetpack.ui.common.BaseConstant

class DetailActivity : AppCompatActivity() {


    companion object {
        fun startDetailActivity(context: Context, id: Long?) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(BaseConstant.DETAIL_SHOE_ID, id)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}
