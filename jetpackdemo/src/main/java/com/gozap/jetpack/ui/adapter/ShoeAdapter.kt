package com.gozap.jetpack.ui.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gozap.jetpack.databinding.ShoeRecyclerItemBinding
import com.gozap.jetpack.ui.db.data.Shoe
import com.gozap.jetpack.ui.ui.activity.DetailActivity

/**
 * Create by liuxue on 2020/5/11 0011.
 * description:
 */

class ShoeAdapter constructor(var context: Context) :
    PagedListAdapter<Shoe, ShoeAdapter.MyViewHolder>(ShoeDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ShoeRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val shoe = getItem(position)
        holder.apply {
            bind(onCreateListener(shoe!!.id), shoe)
            itemView.tag = shoe
        }
    }

    /**
     * 点击事件
     */
    fun onCreateListener(id: Long): View.OnClickListener {
        return View.OnClickListener {
            DetailActivity.startDetailActivity(context, id)
        }
    }


    class MyViewHolder(private val binding: ShoeRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Shoe) {
            binding.apply {
                this.listener = listener
                this.shoe = item
                executePendingBindings()
            }
        }

    }


}