package com.example.halong.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by halong on 2017/7/25.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    public static String Table_Name = "users";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDBHelper(Context context) {
        super(context, "test6", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("drop table if exists "+Table_Name);
        sqLiteDatabase.execSQL("create table " +Table_Name+
                "(id integer primary key autoincrement,name String,age int,height double,isMale boolean)");
        //理论是支持null text integer real blob   实际支持所有基本数据类型byte short int long, double float ,char String, boolean
        // autoincrement  在primary key默认integer autoincrement
        //*boolean:   0->false   1->true
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
