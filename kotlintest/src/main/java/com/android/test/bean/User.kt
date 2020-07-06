package com.android.test.bean

/**
 * Create by liuxue on 2020/4/28 0028.
 * description:
 */

class User constructor() {
    var name: String = "lilei"
    var age: String = "25"
    var city: City? = null

    constructor(name: String, age: String) : this() {
        this.name = name;
        this.age = age
    }

    override fun toString(): String {
        return name + "的年龄是" + age
    }


}

