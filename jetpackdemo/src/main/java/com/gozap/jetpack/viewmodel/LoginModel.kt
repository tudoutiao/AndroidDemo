package com.gozap.jetpack.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.gozap.jetpack.ui.BaseApplication.Companion.context
import com.gozap.jetpack.ui.MainActivity
import com.gozap.jetpack.ui.common.BaseConstant
import com.gozap.jetpack.ui.common.listener.SimpleWatcher

/**
 * Create by liuxue on 2020/5/8 0008.
 * description:
 */
class LoginModel constructor(name: String, pwd: String, context: Context) {
    var n = ObservableField<String>(name)
    var p = ObservableField<String>(pwd)

    //----------------------------------------------------------------------------------//

    /**
     * 用户名改变
     * android:onTextChanged="@{(text, start, before, count)->model.onNameChanged(text)}"
     */
    fun onNameChanged(s: CharSequence) {
        n.set(s.toString())
    }

    /**
     * 密码改变
     * android:onTextChanged="@{(text, start, before, count)->model.onPwdChanged(text)}"
     */
    fun onPwdChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        p.set(s.toString())
    }

    //----------------------------------------------------------------------------------//

    // SimpleWatcher 是简化了的TextWatcher
    val nameWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            n.set(s.toString())
        }
    }

    val pwdWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            p.set(s.toString())
        }
    }


    fun login() {
        if (n.get().equals(BaseConstant.USER_NAME) && p.get().equals(BaseConstant.USER_PWD)) {
            Toast.makeText(context, "账号密码正确", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}