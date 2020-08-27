package com.gozap.basetest.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Create by liuxue on 2020/8/26 0026.
 * description:
 */
public class DBHelper  //数据库版本号
    (context: Context?) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        // 数据库名
        val DATABASE_NAME = "finch.db"

        // 表名
        val USER_TABLE_NAME = "user"
        val JOB_TABLE_NAME = "job"
        val DATABASE_VERSION = 1
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,user_id INTEGER, name TEXT);")
        db.execSQL("CREATE TABLE IF NOT EXISTS " + JOB_TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,job_id INTEGER, job TEXT);")
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
    }

}