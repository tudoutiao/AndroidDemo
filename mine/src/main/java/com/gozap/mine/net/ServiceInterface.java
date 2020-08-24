package com.gozap.mine.net;

import com.gozap.mine.entity.TodayData;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Create by liuxue on 2020/8/19 0019.
 * description:
 */
public interface ServiceInterface {

    @GET("today")
    Observable<TodayData> getDayGank();

}
