package com.android.mylibrary.adapter

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by denglijun on 2019/6/25 18:00
 * description:
 */
open class BaseRecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @SuppressWarnings("unchecked")
    fun <T : View> View.findViewOften(viewId: Int): T? {
        var viewHolder: SparseArray<View> = tag as? SparseArray<View> ?: SparseArray()
        tag = viewHolder
        var childView: View? = viewHolder.get(viewId)
        if (null == childView) {
            childView = findViewById(viewId)
            childView?.let {
                viewHolder.put(viewId, childView)
            }
        }
        return childView as? T
    }

    fun <T : View> findView(viewId: Int): T? {
        return itemView.findViewOften(viewId)
    }
}