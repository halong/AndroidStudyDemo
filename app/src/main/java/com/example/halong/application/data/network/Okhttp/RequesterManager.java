package com.example.halong.application.data.network.Okhttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by halong on 2018/1/28.
 */

public class RequesterManager {
    private static RequesterManager mRequesterManager;
    private static OkHttpClient mOkHttpClient;
    private RequesterManager(){
        mOkHttpClient=new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3,TimeUnit.SECONDS)
                .writeTimeout(3,TimeUnit.SECONDS)
                .build();

    }

    public static RequesterManager getInstance(){
        if (mRequesterManager==null){
            synchronized (RequesterManager.class){
                mRequesterManager=new RequesterManager();
            }
        }
        return mRequesterManager;
    }

    public OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }




}
