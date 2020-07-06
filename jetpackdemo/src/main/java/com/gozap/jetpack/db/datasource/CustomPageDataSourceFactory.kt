package com.gozap.jetpack.ui.db.datasource

import androidx.paging.DataSource
import com.gozap.jetpack.ui.db.data.Shoe
import com.gozap.jetpack.ui.db.respository.ShoeRepository

/**
 * Create by liuxue on 2020/5/11 0011.
 * description:
 * 构建CustomPageDataSource的工厂
 */

class CustomPageDataSourceFactory(private val shoeRepository: ShoeRepository) :
    DataSource.Factory<Int, Shoe>() {
    override fun create(): DataSource<Int, Shoe> {
        return CustomPageDataSource(shoeRepository)
    }
}