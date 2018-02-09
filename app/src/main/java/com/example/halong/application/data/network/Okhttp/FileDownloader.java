package com.example.halong.application.data.network.Okhttp;

import okhttp3.Call;

/**
 * Created by halong on 2018/1/30.
 */

public class FileDownloader {
    private String url;
    private Call mCall;

    public FileDownloader(String url){
        this.url=url;
    }

    public FileDownloader url(String url){
        this.url=url;
        return this;
    }


    public void enqueue(){

    }

    public void pause(){

    }

    public void cancel(){

    }

}
