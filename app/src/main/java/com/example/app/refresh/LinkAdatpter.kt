package com.example.app.refresh

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.app.R
import com.example.app.base.BaseRecycleViewHolder
import com.example.app.base.BaseRecyclerViewAdapter

/**
 * Create by liuxue on 2020/7/13 0013.
 * description:
 */

class LinkAdatpter(mContext: Context) :
    BaseRecyclerViewAdapter<Link, BaseRecycleViewHolder>(mContext) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecycleViewHolder {
        return BaseRecycleViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_link_layout, parent, false)
        );
    }

    override fun onBindViewHolder(holder: BaseRecycleViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var data = dataList[position];
        holder.findVIew<TextView>(R.id.title)?.text = data.title
    }

}

