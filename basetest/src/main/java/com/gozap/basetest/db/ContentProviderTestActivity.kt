package com.gozap.basetest.db

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gozap.basetest.R
import com.gozap.basetest.databinding.ActivityProviderTestBinding

/**
 * 进程内数据访问
 */
class ContentProviderTestActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityProviderTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_provider_test
        )

        //设置uri
        var uri_user = Uri.parse("content://com.gozap.basetest.myprovider/user")
        //获取contentReslover
        var contentResolver = getContentResolver()

        dataBinding.addBtn.setOnClickListener {
            var values = ContentValues()
            values.put("user_id", dataBinding.idEdit.text.toString())
            values.put("name", dataBinding.nameEdit.text.toString())
            // 通过ContentResolver 根据URI 向ContentProvider中插入数据
            contentResolver.insert(uri_user, values)
        }

        dataBinding.deleBtn.setOnClickListener {
            contentResolver.delete(uri_user, "name='${dataBinding.nameEdit.text.toString()}'", null)
        }

        dataBinding.updateBtn.setOnClickListener {
            var values = ContentValues()
            values.put("user_id", dataBinding.idEdit.text.toString())
            values.put("name", dataBinding.nameEdit.text.toString())

            contentResolver.update(uri_user, values, "user_id=11", null)
        }

        dataBinding.queryBtn.setOnClickListener {
            // 通过ContentResolver 向ContentProvider中查询数据
            val cursor =
                contentResolver.query(uri_user, arrayOf("user_id", "name"), null, null, null)
            var stringBuffer = StringBuffer()
            while (cursor!!.moveToNext()) {
//                System.out.println(
//                    "query book:" + cursor.getInt(0).toString() + " " + cursor.getString(1)
//                )
                stringBuffer.append(cursor.getInt(0).toString() + " " + cursor.getString(1))
                stringBuffer.append("\n")
                // 将表中数据全部输出
            }
            //关闭游标
            cursor.close()

            dataBinding.result.text = stringBuffer.toString()
        }


//        //对job表进行操作
//        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
//        val uri_job = Uri.parse("content://com.gozap.basetest.myprovider/job")
//
//        // 插入表中数据
//        val values2 = ContentValues()
//        values2.put("job", "NBA Player")
//
//        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
//        contentResolver.insert(uri_job, values2)
//
//        // 通过ContentResolver 向ContentProvider中查询数据
//        val cursor2 = contentResolver.query(uri_job, arrayOf("job_id", "job"), null, null, null)
//        while (cursor2!!.moveToNext()) {
//            System.out.println(
//                "query job:" + cursor2.getInt(0).toString() + " " + cursor2.getString(1)
//            )
//            // 将表中数据全部输出
//        }
//
//        // 关闭游标
//        cursor2.close()
    }
}