package com.example.app.test

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import kotlinx.android.synthetic.main.activity_scroll_test.*

class ScrollTestActivity : AppCompatActivity(), View.OnClickListener {

    var x: Float = 0f;
    var y: Float = 0f;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_test)
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        x = text.x
        y = text.y
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
            }
        }
        return super.onTouchEvent(event)
    }


    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.button -> {
                text.scrollTo(-100, -100)
            }
            R.id.button2 -> {
                text.scrollTo(-10, -10);
            }
            R.id.button3 -> {
                text.scrollTo(x.toInt(), y.toInt());
            }
        }
    }
}
