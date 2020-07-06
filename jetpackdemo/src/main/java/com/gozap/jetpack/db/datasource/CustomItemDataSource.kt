package com.gozap.jetpack.ui.db.datasource

import androidx.paging.ItemKeyedDataSource
import com.gozap.jetpack.ui.db.data.Shoe

/**
 * Create by liuxue on 2020/5/11 0011.
 * description:
 *   自定义ItemKeyedDataSource资源演示Page库的时候使用
 *
 *
 *   如果您需要使用项目 N 中的数据来获取项目 N+1，请使用 ItemKeyedDataSource。例如，如果您要为讨论应用获取会话式评论，则可能需要传递最后一条评论的 ID 以获取下一条评论的内容。
 */

class CustomItemDataSource :ItemKeyedDataSource<Int, Shoe>(){


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Shoe>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Shoe>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Shoe>) {
        TODO("Not yet implemented")
    }

    override fun getKey(item: Shoe): Int {
        TODO("Not yet implemented")
    }

}