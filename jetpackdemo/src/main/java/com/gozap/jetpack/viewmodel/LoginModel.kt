package com.gozap.jetpack.ui.viewmodel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.gozap.jetpack.db.RepositoryProvider
import com.gozap.jetpack.ui.BaseApplication
import com.gozap.jetpack.ui.common.listener.SimpleWatcher
import com.gozap.jetpack.ui.db.data.Shoe
import com.gozap.jetpack.ui.db.data.User
import com.gozap.jetpack.ui.db.respository.UserRepository

/**
 * Create by liuxue on 2020/5/8 0008.
 * description:
 */
class LoginModel constructor(val repository: UserRepository) : ViewModel() {
    val n = MutableLiveData("")
    val p = MutableLiveData("")
    val enable = MutableLiveData(false)

    /*val n = ObservableField<String>("")
    val p = ObservableField<String>("")*/
    //----------------------------------------------------------------------------------//

    /**
     * 用户名改变
     * android:onTextChanged="@{(text, start, before, count)->model.onNameChanged(text)}"
     */
    fun onNameChanged(s: CharSequence) {
        /* n.set(s.toString())*/
        n.value = s.toString()
        judgeEnable()
    }

    /**
     * 密码改变
     * android:onTextChanged="@{(text, start, before, count)->model.onPwdChanged(text)}"
     */
    fun onPwdChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        /*p.set(s.toString())*/
        p.value = s.toString()
        judgeEnable()
    }

    private fun judgeEnable() {
        enable.value = n.value!!.isNotEmpty() && p.value!!.isNotEmpty()
    }


    //----------------------------------------------------------------------------------//

    // SimpleWatcher 是简化了的TextWatcher
    val nameWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            /*n.set(s.toString())*/
            n.value = s.toString()
            judgeEnable()
        }
    }

    val pwdWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            /*p.set(s.toString())*/
            p.value = s.toString()
            judgeEnable()
        }
    }


    fun login(): LiveData<User?> {
        var name = n.value!!
        var pwd = p.value!!
        return repository.login(name, pwd)

//        if (n.get().equals(BaseConstant.USER_NAME) && p.get().equals(BaseConstant.USER_PWD)) {
//            Toast.makeText(context, "账号密码正确", Toast.LENGTH_SHORT).show()
//            val intent = Intent(context, MainActivity::class.java)
//            context.startActivity(intent)
//        }
    }


    /**
     * 第一次启动的时候调用
     */
    fun onFirstLaunch():String {
        val context = BaseApplication.context
        context.assets.open("shoes.json").use {
            JsonReader(it.reader()).use {
                val shoeType = object : TypeToken<List<Shoe>>() {}.type

                val shoeList: List<Shoe> = Gson().fromJson(it, shoeType)

                val shoeDao = RepositoryProvider.providerShoeRepository(context)
                shoeDao.insertShoes(shoeList)
                for (i in 0..2) {
                    for (shoe in shoeList) {
                        shoe.id += shoeList.size
                    }
                    shoeDao.insertShoes(shoeList)
                }
            }

        }
        return "初始化数据成功！"
    }


}