package com.test.okamiy.sqlite.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Okamiy on 2017/11/17.
 * Email: 542839122@qq.com
 * 数据库文件一般存放在：/data/data/<package name>/databases/ 目录
 */

public class MySQliteHelper extends SQLiteOpenHelper {

    /**
     * 建表
     */
    public static final String CREATE_BOOK = "create table Book("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";
    private Context mContext;

    /**
     * @param context 上下文
     * @param name    数据库名称，创建数据库时使用
     * @param factory 一般传入null，查询数据库的时候返回一个自定义的Cursor
     * @param version 当前数据库的版本号，可用于升级
     */
    public MySQliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext, "Create MyDatabase Succeeded !", Toast.LENGTH_LONG).show();
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 创建或者打开一个数据库，会调用oncreat方法
     *
     * @return 返回一个可对数据库进行读写操作的对象（当数据库不可写入时（如磁盘满了），次方法返回的对象将以只读的方式去打开数据库）
     */
    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    /**
     * 创建或者打开一个数据库，会调用oncreat方法
     *
     * @return 返回一个可对数据库进行读写操作的对象（当数据库不可写入时（如磁盘满了），次方法会出现异常）
     */
    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    /**
     *  Book 标准建表语句：
     *
     *  一张包含：主键id、作者、价格、页数、书名 的表
     *  解释：
     *      integer 整型，text 文本类型，blob 二进制类型，real 浮点型
     *      primary key 将id列设为主键，并用autoincrement关键字表明id列是自增长的
     *
     *  create table Book(
     *      id integer primary key autioncrement,
     *      author text,
     *      price real,
     *      pages integer,
     *      name text)
     */
}
