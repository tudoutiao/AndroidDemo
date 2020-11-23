package com.gozap.mine.net

import com.gozap.mine.entity.Categories
import com.gozap.mine.entity.TodayData
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Create by liuxue on 2020/8/19 0019.
 * description:
 */
interface ServiceInterface {
    @get:GET("today")
    val dayGank: Observable<TodayData?>

    @get:GET("/v2/categories/GanHuo")
    val categories: Observable<Categories?>


}