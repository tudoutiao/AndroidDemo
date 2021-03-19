package com.example.app.swip

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.app.R
import java.util.*

/**
 * Create by liuxue on 2020/6/29 0029.
 * description:
 */
class ItemAdapter(mShowItems: List<Bean?>?) :
    BaseQuickAdapter<Bean, BaseViewHolder>(R.layout.layout_item_swipe, mShowItems) {
    override fun convert(helper: BaseViewHolder, item: Bean) {
        val mSwipe = helper.getView<SwipeMenuLayout>(R.id.swipe_menu_layout)
        val position = helper.layoutPosition
        var text = item.title + "，菜单在右； "
        if (position % 3 == 0) {
            mSwipe.isOpenChoke = false
            text += "我是无阻塞的； "
        } else {
            mSwipe.isOpenChoke = true
            text += "我是有阻塞的； "
        }
        if (position % 5 == 0) {
            mSwipe.isClickMenuAndClose = true
            text += "点击我可以展开菜单"
        } else {
            mSwipe.isClickMenuAndClose = false
        }
        (helper.getView<View>(R.id.tv_content) as TextView).text = text

        if (item.imgList!!.size > 0) {
            (helper.getView<View>(R.id.imgList) as ImageListView).addImageList(item.imgList as ArrayList<String>?)
            (helper.getView<View>(R.id.imgList) as ImageListView).visibility = View.VISIBLE
        } else {
            (helper.getView<View>(R.id.imgList) as ImageListView).visibility = View.GONE
        }
        helper.addOnClickListener(R.id.tv_menu1).addOnClickListener(R.id.tv_menu2)
    }
}