package com.gozap.jetpack.ui.db.data

import androidx.room.*

/**
 * Create by liuxue on 2020/5/9 0009.
 * description:
 *              用户表
 */

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "user_account") val account: String//账号
    , @ColumnInfo(name = "user_pwd") val pwd: String // 密码
    , @ColumnInfo(name = "user_name") val name: String
    , @ColumnInfo(name = "user_url") var headImage:String // 头像地址

//    , @Embedded val address: Address // 地址
//    , @Ignore val state: Int // 状态只是临时用，所以不需要存储在数据库中
) {
    @PrimaryKey(autoGenerate = true)//主键自增
    @ColumnInfo(name = "id")
    var id: Long = 0


}