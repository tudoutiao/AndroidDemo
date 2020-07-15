package com.example.app.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.app.App.Companion.context
import com.example.app.R

/**
 * Create by liuxue on 2020/6/29 0029.
 * description:
 */

class Utils {
    var requestOptions: RequestOptions =
        RequestOptions().centerCrop()
            .placeholder(R.mipmap.ic_main_list_default_image)
            .error(R.mipmap.ic_main_list_default_image)

    fun d2p(dipValue: Float): Int {
        return d2p(context!!, dipValue);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun d2p(context: Context, dipValue: Float): Int {
        val scale =
            context.applicationContext.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(pxValue: Float): Int {
        val scale = context!!.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }


    fun setImageList(
        imgUrl: String?,
        imageView: ImageView,
        mContext: Context
    ) {
        if (TextUtils.isEmpty(imgUrl)) {
            imageView.setImageResource(R.mipmap.ic_main_list_default_image)
            return
        }
        if (!isDestroy(mContext as Activity)) Glide.with(mContext).load(imgUrl)
            .apply(requestOptions).into(imageView)
    }


    fun isDestroy(mContext: Activity): Boolean {
        return if (mContext == null || mContext.isFinishing() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && mContext.isDestroyed()) {
            true
        } else {
            false
        }
    }

    var SCREEN_WIDTH_PX_CACHE = -1
    var SCREEN_HEIGHT_PX_CACHE = -1

    fun getScreenWidthPx(context: Context): Int {
        if (SCREEN_WIDTH_PX_CACHE < 0) {
            val display = (context as Activity).windowManager.defaultDisplay
            SCREEN_WIDTH_PX_CACHE = display.width
        }
        return SCREEN_WIDTH_PX_CACHE
    }

}