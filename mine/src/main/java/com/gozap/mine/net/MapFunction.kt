package com.gozap.mine.net

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

/**
 * Create by liuxue on 2020/11/5 0005.
 * description:
 */

class BaseException constructor(val code: String, val msg: String) : Throwable()


inline fun <reified T : Any> new(vararg params: Any) =
    T::class.java.getDeclaredConstructor(*params.map { it::class.java }.toTypedArray())
        .apply { isAccessible = true }.newInstance(*params)

class MapperFunction<T> : Function<BaseResponse<T>, ObservableSource<T>> {
    override fun apply(t: BaseResponse<T>): ObservableSource<T> {
        if (t.data == null) {
            return Observable.just(new<Any>() as T)
        }
        return Observable.just(t.data)
    }
}

fun <T> Observable<BaseResponse<T>>.convert(): Observable<T> {
    return this.flatMap(MapperFunction())
}

fun <T> BaseResponse<T>.check(): BaseResponse<T> {
    if ("000".equals(this.code)) {
        return this
    } else {
        throw BaseException(this.code, this.msg ?: "")
    }
}
