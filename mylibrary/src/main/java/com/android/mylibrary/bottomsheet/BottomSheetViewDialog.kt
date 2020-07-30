package com.android.mylibrary.bottomsheet

import android.content.Context
import android.content.DialogInterface
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mylibrary.R
import com.android.mylibrary.adapter.TitleAdapter
import com.android.mylibrary.util.Utils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog_bottom_sheet.*

/**
 * Create by liuxue on 2020/7/29 0029.
 * description:直接使用BottomSheetDialog
 */

open class BottomSheetViewDialog(context: Context, listener: DialogInterface.OnClickListener) :
    BottomSheetDialog(context) {
    var dataList = mutableListOf<String>()

    val viewAdapter by lazy {
        TitleAdapter(context)
    }
    val viewManager by lazy {
        LinearLayoutManager(context)
    }

    init {
        window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet, null)
        setContentView(view)

        //设置圆角背景不生效 原因：由于它上面蒙了一层布局 design_bottom_sheet是系统的布局，直接找到它，然后给它设全透明就好了
        var bottomSheet =
            getDelegate().findViewById<View>(com.google.android.material.R.id.design_bottom_sheet);
        bottomSheet!!.setBackgroundColor(context.getResources().getColor(R.color.transparent));

        //设置dialog的下拉拖拽与scrollView下拉冲突
        var bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        var peekHeigth = context.resources.displayMetrics.heightPixels
        bottomSheetBehavior.setPeekHeight(peekHeigth - 200);//此处是dialog设置的最大高度

        viewAdapter.dataList = dataList
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    open fun refreshTopicList(list: MutableList<String>) {
        dataList.addAll(list)
        viewAdapter.notifyDataSetChanged()
    }


    override fun show() {
        super.show()
        setHeight()
    }

    /**
     * 避免在list数据减少是dialog高度变化
     */
    fun setHeight() {
        var params: WindowManager.LayoutParams = getWindow()!!.getAttributes()
        params.height = Utils.getWindowHeight(context) - 200
        var mainParams = mainView.layoutParams
        mainParams.height = params.height

        mainView.layoutParams = mainParams
        getWindow()!!.setAttributes(params)
        getWindow()!!.setGravity(Gravity.BOTTOM)
    }


}