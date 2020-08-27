package com.android.test.test

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.test.R

class ProvideTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provide_test)
        val uri_user = Uri.parse("content://com.gozap.basetest.myprovider/user")

        var values = ContentValues()
        values.put("user_id", 15)
        values.put("name", "呱唧")

        var resolver = contentResolver
        resolver.insert(uri_user, values)

        // 通过ContentResolver 向ContentProvider中查询数据

        // 通过ContentResolver 向ContentProvider中查询数据
        val cursor =
            resolver.query(uri_user, arrayOf("user_id", "name"), null, null, null)
        while (cursor!!.moveToNext()) {
            System.out.println(
                "query user:" + cursor.getInt(0).toString() + " " + cursor.getString(1)
            )
            // 将表中数据全部输出
        }
        cursor.close()
    }
}