package com.example.halong.myapplication.OkHttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by halong on 2017/11/12.
 */

public class OkHttpClientSingleton {
    private OkHttpClientSingleton(){

    }

    public static OkHttpClient getOkHttpClient(){
        return OkHttpClientHolder.mInstance;
    }

    private static class OkHttpClientHolder{
        private static final OkHttpClient mInstance = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .build();
    }
}
