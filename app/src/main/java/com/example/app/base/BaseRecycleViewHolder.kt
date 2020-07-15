package com.example.app.base

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by liuxue on 2020/7/13 0013.
 * description:
 */
open class BaseRecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun <T : View> View.findViewOften(viewId: Int): T {
        var viewHolder = tag as? SparseArray<View> ?: SparseArray()
        tag = viewHolder
        var childView: View? = viewHolder.get(viewId)
        if (null == childView) {
            childView = findViewById(viewId)
            viewHolder.put(viewId, childView);
        }
        return childView as T
    }

    fun <T : View> findVIew(viewId: Int): T {
        return itemView.findViewOften(viewId)
    }

}