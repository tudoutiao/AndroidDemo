package com.gozap.jetpack.ui.util

import com.gozap.jetpack.ui.BaseApplication
import com.tencent.mmkv.MMKV


/**
 * Create by liuxue on 2020/5/7 0007.
 * description:
 */

object AppPrefsUtils  {
    var dir = BaseApplication.context.filesDir.absolutePath + "/mmkv"
    var rootDir = MMKV.initialize(dir)
    lateinit var mk: MMKV

    init {
        mk = MMKV.defaultMMKV()
    }


    /*
         Boolean数据
      */
    fun putBoolean(key: String, value: Boolean) {
        mk.encode(key, value)
    }

    /*
        默认 false
     */
    fun getBoolean(key: String): Boolean {
        return mk.decodeBool(key, true)
    }

    /*
        String数据
     */
    fun putString(key: String, value: String) {
        mk.encode(key, value)
    }

    /*
        默认 ""
     */
    fun getString(key: String): String {
        return mk.decodeString(key, "")
    }

    /*
        Int数据
     */
    fun putInt(key: String, value: Int) {
        mk.encode(key, value)
    }

    /*
        默认 0
     */
    fun getInt(key: String): Int {
        return mk.decodeInt(key, 0)
    }

    /*
        Long数据
     */
    fun putLong(key: String, value: Long) {
        mk.encode(key, value)
    }

    /*
        默认 0
     */
    fun getLong(key: String): Long {
        return mk.decodeLong(key, 0)
    }

    /*
        Set数据
     */
    fun putStringSet(key: String, set: Set<String>) {
        val localSet = getStringSet(key).toMutableSet()
        localSet.addAll(set)
        mk.encode(key, localSet)
    }

    /*
        默认空set
     */
    fun getStringSet(key: String): Set<String> {
        val set = setOf<String>()
        return mk.decodeStringSet(key, set)
    }

    /*
        删除key数据
     */
    fun remove(key: String) {
        mk.remove(key)
    }
}