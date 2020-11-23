package com.gozap.mine.entity

import com.google.gson.annotations.SerializedName

/**
 * Create by liuxue on 2020/11/5 0005.
 * description: 干活类别
 */

class Categories {
    @SerializedName("_id")
    var _id: String? = null

    @SerializedName("coverImageUrl")
    var coverImageUrl: String? = null

    @SerializedName("desc")
    var desc: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("type")
    var type: String? = null


}