package com.android.mylibrary.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by liuxue on 2020/7/29 0029.
 * description:
 */
abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(var context: Context) :
    RecyclerView.Adapter<VH>() {
    var itemClickListener: OnItemClickListener? = null

    var dataList: MutableList<T> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount() = dataList.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}