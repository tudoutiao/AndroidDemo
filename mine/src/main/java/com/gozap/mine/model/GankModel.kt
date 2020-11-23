package com.gozap.mine.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.gozap.mine.config.Constants
import com.gozap.mine.net.AppClient
import com.gozap.mine.net.ServiceInterface
import kotlinx.coroutines.CoroutineScope

/**
 * Create by liuxue on 2020/8/19 0019.
 * description:
 */

@RequiresApi(Build.VERSION_CODES.N)
class GankModel : ViewModel() {
    val apiClient: AppClient by lazy {
        AppClient.getInstance()
    }

    init {
        apiClient.create(ServiceInterface::class.java, Constants.BASE_URL)
    }



}