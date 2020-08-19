package com.gozap.mine.entity

import com.google.gson.annotations.SerializedName

/**
 * Create by liuxue on 2020/8/19 0019.
 * description:
 */

class TodayData {
    @SerializedName("Android")
    var androidList: List<Gank>? = null

    @SerializedName("iOS")
    var iosList: List<Gank>? = null

    @SerializedName("福利")
    var welfareList: List<Gank>? = null

    @SerializedName("拓展资源")
    var extraList: List<Gank>? = null

    @SerializedName("前端")
    var frontEndList: List<Gank>? = null

    @SerializedName("瞎推荐")
    var casualList: List<Gank>? = null

    @SerializedName("App")
    var appList: List<Gank>? = null

    @SerializedName("休息视频")
    var videoList: List<Gank>? = null

}