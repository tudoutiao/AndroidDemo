package com.android.test.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.test.R

/**
 * Create by liuxue on 2020/5/14 0014.
 * description:
 */
class MyAdatper(val context: Context, val arrayList: ArrayList<String>) :
    RecyclerView.Adapter<VH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var v = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false)
        return VH(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val title = arrayList.get(position)
        holder.title.setText(title)
    }


}


class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.text)
}
