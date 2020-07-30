package com.android.mylibrary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.android.mylibrary.R

/**
 * Create by liuxue on 2020/7/29 0029.
 * description:只有一个文本内容得适配器
 */
class TitleAdapter(context: Context) :
    BaseRecyclerViewAdapter<String, BaseRecycleViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecycleViewHolder {
        return BaseRecycleViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_sample_title, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseRecycleViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val data = dataList[position]
        holder.findView<TextView>(R.id.title)?.text = data
    }

}
