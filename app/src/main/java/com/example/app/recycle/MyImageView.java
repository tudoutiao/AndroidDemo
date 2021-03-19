package com.example.app.recycle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.app.recycle.datapter.Test1Adapter;

/**
 * Create by liuxue on 2021/2/24 0024.
 * description:
 */
public class MyImageView extends androidx.appcompat.widget.AppCompatImageView {
    public MyImageView(Context context) {
        super(context);
        init(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
    }

    private ItemTouchHelper itemTouchHelper;
    private Test1Adapter.MyViewHolder holder;

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper, Test1Adapter.MyViewHolder holder) {
        this.itemTouchHelper = itemTouchHelper;
        this.holder = holder;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("Test1", "dispatchTouchEvent :" + event.getAction());
        return super.dispatchTouchEvent(event);
//        if (event.getAction() == MotionEvent.ACTION_MOVE) {
//            getParent().requestDisallowInterceptTouchEvent(true);
//            if (null != itemTouchHelper) {
//                itemTouchHelper.startDrag(holder);
//            }
//            return true;
//        } else {
//            getParent().requestDisallowInterceptTouchEvent(false);
//        }
//        return false;
    }
}
