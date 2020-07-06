package com.gozap.jetpack.ui.db.data

import androidx.room.*
import java.util.*

/**
 * Create by liuxue on 2020/5/9 0009.
 * description:
 *             喜欢的鞋子表
 *
 * @ForeignKey 注释定义该实体与 Shoe和User 实体的关系
 */

@Entity(
    tableName = "fav_shoe",
    foreignKeys = [
        ForeignKey(
            entity = Shoe::class,
            parentColumns = ["id"],
            childColumns = ["shoe_id"]
        ),

        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"]
        )
    ],
    indices = [Index("shoe_id")]
)
data class FavouriteShoe(
    @ColumnInfo(name = "shoe_id") val shoeId: Long // 外键 鞋子的id
    , @ColumnInfo(name = "user_id") val userId: Long // 外键 用户的id
    , @ColumnInfo(name = "fav_date") val date: Calendar // 创建日期
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}