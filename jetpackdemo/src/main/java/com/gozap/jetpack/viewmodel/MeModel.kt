package com.gozap.jetpack.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.gozap.jetpack.ui.BaseApplication
import com.gozap.jetpack.ui.common.BaseConstant
import com.gozap.jetpack.ui.common.BaseConstant.IMAGE_MANIPULATION_WORK_NAME
import com.gozap.jetpack.ui.common.BaseConstant.KEY_IMAGE_URI
import com.gozap.jetpack.ui.common.BaseConstant.TAG_OUTPUT
import com.gozap.jetpack.ui.db.respository.UserRepository
import com.gozap.jetpack.ui.util.AppPrefsUtils
import com.joe.jetpackdemo.worker.BlurWorker
import com.joe.jetpackdemo.worker.CleanUpWorker
import com.joe.jetpackdemo.worker.SaveImageToFileWorker

/**
 * Create by liuxue on 2020/8/4 0004.
 * description:
 */

class MeModel(val userRepository: UserRepository) : ViewModel() {
    internal var imageUri: Uri? = null
    internal var outPutUri: Uri? = null
    internal var outPutWorkInfos: LiveData<List<WorkInfo>>
    private val workManager = WorkManager.getInstance(BaseApplication.context)
    val user = userRepository.findUserById(AppPrefsUtils.getLong(BaseConstant.SP_USER_ID))

    init {
        //返回 LiveData 和具有该标记的所有任务的状态列表。
        outPutWorkInfos = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)
    }

    fun applyBlur(blurLevel: Int) {

        var continuation = workManager
            .beginUniqueWork(   // 创建唯一工作链
                IMAGE_MANIPULATION_WORK_NAME,//任务名称
                ExistingWorkPolicy.REPLACE,  // 任务相同的执行策略 分为REPLACE，KEEP，APPEND
                OneTimeWorkRequest.from(CleanUpWorker::class.java)
            )

        for (i in 0 until blurLevel) {
            val builder = OneTimeWorkRequestBuilder<BlurWorker>()
            if (i == 0) {
                builder.setInputData(createInputDataForUri())
            }
            continuation = continuation.then(builder.build())
        }

        // 构建约束条件
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true) // 非电池低电量
            .setRequiredNetworkType(NetworkType.CONNECTED) // 网络连接的情况
            .setRequiresStorageNotLow(true) // 存储空间足
            .build()

        // 储存照片
        val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()

        continuation = continuation.then(save)

        continuation.enqueue()

    }


    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        imageUri?.let {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
        }
        return builder.build()
    }

}