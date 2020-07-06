package com.gozap.jetpack.ui.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Create by liuxue on 2020/5/9 0009.
 * description:
 *              鞋子表
 */

@Entity(tableName = "shoe")
data class Shoe(
    @ColumnInfo(name = "shoe_name") val name: String
    , @ColumnInfo(name = "shoe_description") val description: String
    , @ColumnInfo(name = "shoe_price") val price: Float
    , @ColumnInfo(name = "shoe_brand") val brand: String
    , @ColumnInfo(name = "shoe_imgUrl") val imageUrl: String
) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0


    fun getPriceStr():String{
        return price.toString()
    }
}