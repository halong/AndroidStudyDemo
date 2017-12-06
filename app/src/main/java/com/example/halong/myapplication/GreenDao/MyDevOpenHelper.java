package com.example.halong.myapplication.GreenDao;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by halong on 2017/12/4.
 */

public class MyDevOpenHelper extends DaoMaster.DevOpenHelper {
    public MyDevOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
