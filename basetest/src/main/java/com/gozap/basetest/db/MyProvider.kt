package com.gozap.basetest.db

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

/**
 * Create by liuxue on 2020/8/26 0026.
 * description:
 */
class MyProvider : ContentProvider() {
    lateinit var mContext: Context
    lateinit var mDbHelper: DBHelper
    lateinit var db: SQLiteDatabase

    //用于辅助ContentProvider，用于在ContentProvider中注册URI。根据URI匹配ContentProvider中对应的数据表。
    //经过注册后， 就可以在使用uri时通过匹配不同的结果在switch语句里做不同的处理了
    val uriManager by lazy {
        UriMatcher(UriMatcher.NO_MATCH)
    }
    var AUTOHORITY = "com.gozap.basetest.myprovider"
    val User_Code = 1
    val Job_Code = 2

    init {
        uriManager.addURI(AUTOHORITY, "user", User_Code)
        uriManager.addURI(AUTOHORITY, "job", Job_Code)
    }

    override fun onCreate(): Boolean {
        mContext = this.context!!
        // 在ContentProvider创建时对数据库进行初始化
        // 运行在主线程，故不能做耗时操作,此处仅作展示
        mDbHelper = DBHelper(getContext());
        db = mDbHelper.getWritableDatabase();

        // 初始化两个表的数据(先清空两个表,再各加入一个记录)
        db.execSQL("delete from user");
        db.execSQL("insert into user (user_id,name) values(11,'Carson')");

        db.execSQL("delete from job");
        db.execSQL("insert into job (job_id,job) values(21,'Android')");

        return true;
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        val table: String = getTableName(uri)

        // 向该表添加数据
        db.insert(table, null, values)

        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        mContext.contentResolver.notifyChange(uri, null)

        // 通过ContentUris类从URL中获取ID
        // long personid = ContentUris.parseId(uri);
        // System.out.println(personid);
        return uri
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        val table = getTableName(uri)

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);

        // 查询数据
        return db.query(table, projection, selection, selectionArgs, null, null, sortOrder, null)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val table = getTableName(uri)
        return db.update(table, values, selection, selectionArgs)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val table = getTableName(uri)
        return db.delete(table, selection, selectionArgs)
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     */
    fun getTableName(uri: Uri): String {
        var tableName = ""
        when (uriManager.match(uri)) {
            User_Code -> tableName = DBHelper.USER_TABLE_NAME
            Job_Code -> tableName = DBHelper.JOB_TABLE_NAME
        }
        return tableName
    }


}