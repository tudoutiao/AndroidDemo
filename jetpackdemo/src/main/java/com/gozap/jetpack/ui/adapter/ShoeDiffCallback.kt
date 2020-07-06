package com.gozap.jetpack.ui.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gozap.jetpack.ui.db.data.Shoe

/**
 * Create by liuxue on 2020/5/11 0011.
 * description:
 */

class ShoeDiffCallback :DiffUtil.ItemCallback<Shoe>(){

    //返回值表示新数据传入时这两个位置的数据是否是同一个条目
    //当areItemsTheSame返回为false时，不管areContentsTheSame是否为true，adapter中的条目都会更新
    override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem.id == newItem.id
    }

    //返回值表示新老位置的数据内容是否相同，这个方法在areItemsTheSame（）返回true时生效
    override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem == newItem
    }

    //当areItemsTheSame()返回true，同时areContentsTheSame()返回false时，通过这个方法可以用来返回具体的数据差异。
    override fun getChangePayload(oldItem: Shoe, newItem: Shoe): Any? {
        return super.getChangePayload(oldItem, newItem)
    }

}