package com.gozap.mine.net

import java.io.Serializable

/**
 * Create by liuxue on 2020/11/5 0005.
 * description:
 */
open class BaseResponse<T>(
    val code: String,
    val msg: String? = null,
    val data: T? = null,
    val traceID: String? = null
) : Serializable