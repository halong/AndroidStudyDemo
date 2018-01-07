package com.example.halong.myapplication.data.database.GreenDao;

import android.content.Context;

import com.orhanobut.logger.Logger;

/**
 * Created by halong on 2017/12/3.
 */

public class GreenDaoUtil {
    private static DaoSession daoSession;
    private static UserDao userDao;
    private GreenDaoUtil(){
    }

    public static void init(Context c){
        DaoMaster.DevOpenHelper helper=new MyDevOpenHelper(c,"mydb.db");
        DaoMaster daoMaster=new DaoMaster(helper.getWritableDb());
        daoSession=daoMaster.newSession();
    }

    public static UserDao getUserDao(){
        if(userDao==null){
            userDao=daoSession.getUserDao();
        }
        return userDao;
    }

}
