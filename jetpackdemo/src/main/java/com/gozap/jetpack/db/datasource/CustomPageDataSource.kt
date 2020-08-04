package com.gozap.jetpack.ui.db.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.gozap.jetpack.ui.common.BaseConstant
import com.gozap.jetpack.ui.db.data.Shoe
import com.gozap.jetpack.ui.db.respository.ShoeRepository

/**
 * Create by liuxue on 2020/5/11 0011.
 * description:
 * 自定义PageKeyedDataSource
 * 演示Page库的时候使用
 *
 * 如果您加载的网页嵌入了上一页/下一页的键，请使用 PageKeyedDataSource
 */

class CustomPageDataSource (var shoeRepository: ShoeRepository):PageKeyedDataSource<Int,Shoe>(){

    private val TAG: String by lazy {
        this::class.java.simpleName
    }

    // 第一次加载的时候调用
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Shoe>
    ) {
        val startIndex = 0L
        val endIndex: Long = 0L + params.requestedLoadSize
        val shoes = shoeRepository.getPageShoes(startIndex, endIndex)

        callback.onResult(shoes, null, 2)

    }

    // 每次分页加载的时候调用
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Shoe>) {
        Log.e(TAG, "startPage:${params.key},size:${params.requestedLoadSize}")

        val startPage = params.key
        val startIndex = ((startPage - 1) * BaseConstant.SINGLE_PAGE_SIZE).toLong() + 1
        val endIndex = startIndex + params.requestedLoadSize - 1
        val shoes = shoeRepository.getPageShoes(startIndex, endIndex)

        callback.onResult(shoes, params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Shoe>) {
        Log.e(TAG, "endPage:${params.key},size:${params.requestedLoadSize}")
        val endPage = params.key
        val endIndex = ((endPage - 1) * BaseConstant.SINGLE_PAGE_SIZE).toLong() + 1
        var startIndex = endIndex - params.requestedLoadSize
        startIndex = if (startIndex < 0) 0L else startIndex
        val shoes = shoeRepository.getPageShoes(startIndex, endIndex)

        callback.onResult(shoes, params.key + 1)
    }

}