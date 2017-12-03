package com.example.halong.myapplication;

import android.app.Application;

import com.example.halong.myapplication.GreenDao.GreenDaoHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by halong on 2017/7/25.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化orhanobut Logger
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        GreenDaoHelper.init(this);

    }
}
