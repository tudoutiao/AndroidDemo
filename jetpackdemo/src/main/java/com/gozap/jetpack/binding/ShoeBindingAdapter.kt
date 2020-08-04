package com.gozap.jetpack.ui.binding

import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gozap.jetpack.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Create by liuxue on 2020/5/9 0009.
 * description:
 * @BindingAdapter:自定义属性
 *
 */

/**
 *记载ImageView
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if(!imageUrl.isNullOrEmpty()){
        Glide.with(view.context)
            .asBitmap()
            .load(imageUrl)
            .placeholder(R.mipmap.glide_placeholder)
            .centerCrop()
            .into(view)
    }
}

/**
 * 加载带圆角的头像
 *
 */
@BindingAdapter("imageTransFromUrl")
fun bindImageTransFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(
                RequestOptions.bitmapTransform(
                    RoundedCornersTransformation(
                        20,
                        0,
                        RoundedCornersTransformation.CornerType.ALL
                    )
                )
            )
            .into(view)
    }
}


/**
 *文本监听器
 */
@BindingAdapter("addTextChangedListener")
fun addTextChangedListener(editText: EditText, simpleWatcher: TextWatcher) {
    editText.addTextChangedListener(simpleWatcher)
}


