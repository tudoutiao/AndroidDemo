package com.gozap.mine.entity

import java.util.*

/**
 * Create by liuxue on 2020/8/19 0019.
 * description:
 */
class Gank {
    var _id: String? = null
    var createdAt: Date? = null
    var desc: String? = null
    var publishedAt: Date? = null
    var source: String? = null
    var type: String? = null
    var url: String? = null
    var used = false
    var who: String? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val gank = o as Gank
        return _id == gank._id &&
                type == gank.type
    }

    override fun hashCode(): Int {
        return Objects.hash(_id, type)
    }


}