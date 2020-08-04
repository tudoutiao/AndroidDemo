package com.gozap.jetpack.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gozap.jetpack.databinding.FavouriteRecyclerItemBinding
import com.gozap.jetpack.ui.common.BaseConstant
import com.gozap.jetpack.ui.db.data.Shoe
import com.gozap.jetpack.ui.ui.activity.DetailActivity
import com.gozap.jetpack.ui.ui.adapter.ShoeDiffCallback

/**
 * Create by liuxue on 2020/8/4 0004.
 * description:
 */
class FavouriteAdapter(val context: Context) :
    ListAdapter<Shoe, FavouriteAdapter.FavouriteViewHolder>(ShoeDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(
            FavouriteRecyclerItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        var shoe = getItem(position)
        holder.bind(onCreateListener(shoe.id), shoe)
    }

    /**
     * 点击事件
     */
    private fun onCreateListener(id: Long): View.OnClickListener {
        return View.OnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(BaseConstant.DETAIL_SHOE_ID, id)
            context.startActivity(intent)
        }
    }

    class FavouriteViewHolder(private val binding: FavouriteRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Shoe) {
            binding.apply {
                this.listener = listener
                this.shoe = item
                executePendingBindings()
            }
        }
    }

}