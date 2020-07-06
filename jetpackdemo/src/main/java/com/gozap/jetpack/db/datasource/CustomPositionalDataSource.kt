package com.gozap.jetpack.ui.db.datasource

import androidx.paging.PositionalDataSource

/**
 * Create by liuxue on 2020/5/11 0011.
 * description:
 *
 * 如果您需要从数据存储区中选择的任意位置获取数据页，请使用 PositionalDataSource。该类支持从您选择的任意位置开始请求一组数据项。
 * 例如，该请求可能会返回从位置 1500 开始的 50 个数据项。
 */

class CustomPositionalDataSource :PositionalDataSource<Int>(){
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Int>) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Int>) {
        TODO("Not yet implemented")
    }

}