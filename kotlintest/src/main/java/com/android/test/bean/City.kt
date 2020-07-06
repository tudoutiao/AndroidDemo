package com.android.test.bean

/**
 * Create by liuxue on 2020/4/29 0029.
 * description:
 */

class City constructor() {
    var name: String? = null

    constructor(name: String) : this() {
        this.name = name
    }
}