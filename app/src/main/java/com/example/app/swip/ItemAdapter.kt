package com.example.app.swip

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.app.R

/**
 * Create by liuxue on 2020/6/29 0029.
 * description:
 */
class ItemAdapter(mShowItems: List<String?>?) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_item_swipe, mShowItems) {
    override fun convert(helper: BaseViewHolder, item: String) {
        val mSwipe = helper.getView<SwipeMenuLayout>(R.id.swipe_menu_layout)
        val position = helper.layoutPosition
        mSwipe.isEnableLeftMenu = position % 4 == 0
        var text = item + "，菜单在" + if (position % 4 == 0) "左； " else "右； "
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
        helper.addOnClickListener(R.id.tv_menu1).addOnClickListener(R.id.tv_menu2)
    }
}