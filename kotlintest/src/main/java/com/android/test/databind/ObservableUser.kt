package com.android.test.databind

import androidx.databinding.ObservableField

/**
 * Create by liuxue on 2020/4/28 0028.
 * description:
 */

class ObservableUser {
    var name: ObservableField<String> = ObservableField()
    var pwd: ObservableField<String> = ObservableField()

    override fun toString(): String {
        return name.toString() + "--" + pwd.toString()
    }
}