package com.example.halong.myapplication.GreenDao;

import android.content.Context;

/**
 * Created by halong on 2017/12/3.
 */

public class GreenDaoHelper {
    private static DaoSession daoSession;
    private GreenDaoHelper(){

    }

    public static void init(Context c){
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(c,"mydb.db");
        DaoMaster daoMaster=new DaoMaster(helper.getWritableDb());
        daoSession=daoMaster.newSession();
    }

    public static UserDao getUserDao(){
        return daoSession.getUserDao();
    }

}
