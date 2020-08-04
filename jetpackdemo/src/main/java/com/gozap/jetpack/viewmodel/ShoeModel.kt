package com.gozap.jetpack.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gozap.jetpack.common.createPagerList
import com.gozap.jetpack.ui.db.data.Shoe
import com.gozap.jetpack.ui.db.datasource.CustomPageDataSourceFactory
import com.gozap.jetpack.ui.db.respository.ShoeRepository

/**
 * Create by liuxue on 2020/5/9 0009.
 * description:
 */
class ShoeModel constructor(shoeRepository: ShoeRepository) : ViewModel() {
    // 品牌的观察对象 默认观察所有的品牌
    val brand = MutableLiveData<String>().apply {
        value = ALL
    }


    // 鞋子集合的观察类
    val shoes: LiveData<PagedList<Shoe>> = brand.switchMap {

        // Room数据库查询，只要知道返回的是LiveData<List<Shoe>>即可
        if (it == ALL) {
            // LivePagedListBuilder<Int,Shoe>( shoeRepository.getAllShoes(),PagedList.Config.Builder()
            LivePagedListBuilder<Int, Shoe>(
                CustomPageDataSourceFactory(shoeRepository) // DataSourceFactory
                , PagedList.Config.Builder()
                    .setPageSize(10) // 分页加载的数量
                    .setEnablePlaceholders(false) // 当item为null是否使用PlaceHolder展示
                    .setInitialLoadSizeHint(10) // 预加载的数量
                    .build()
            ).build()

            //shoeRepository.getAllShoes()
        } else {
            val array: Array<String> =
                when (it) {
                    NIKE -> arrayOf("Nike", "Air Jordan")
                    ADIDAS -> arrayOf("Adidas")
                    else -> arrayOf(
                        "Converse", "UA"
                        , "ANTA"
                    )
                }
            shoeRepository.getShoesByBrand(array)
                .createPagerList(6, 6)
        }
    }


    // 鞋子集合的观察类
    /*val shoes: LiveData<PagedList<Shoe>> = LivePagedListBuilder<Int, Shoe>(
        CustomPageDataSourceFactory(shoeRepository) // DataSourceFactory
        , PagedList.Config.Builder()
            .setPageSize(10) // 分页加载的数量
            .setEnablePlaceholders(false) // 当item为null是否使用PlaceHolder展示
            .setInitialLoadSizeHint(10) // 预加载的数量
            .build())
        .build()*/


    fun setBrand(brand: String) {
        this.brand.value = brand

        this.brand.map {

        }
    }

    fun clearBrand() {
        this.brand.value = ALL
    }


    companion object {
         const val ALL = "所有"
         const val NIKE = "Nike"
         const val ADIDAS = "Adidas"
         const val OTHER = "other"
    }
}