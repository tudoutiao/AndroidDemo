package com.gozap.jetpack.ui.db.data

import androidx.room.Entity

/**
 * 地址
 */
data class Address(
    val street: String, val state: String, val city: String, val postCode: String
)