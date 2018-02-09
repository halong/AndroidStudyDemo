package com.example.halong.application.data.network.Okhttp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by halong on 2018/1/28.
 * 用于分组管理网络请求对象
 */

public class RequesterGroup {
    List<Loader> mHttpRequesters;
    /**
     * 构造方法：创建RequesterGroup对象
     */
    public void RequesterGroup(){
        mHttpRequesters=new ArrayList<>();
    }

    public StringDownloader newGetRequester(){
        return newGetRequester();
    }


    /**
     * 取消该组中指定的网络请求
     */
    public void cancel(Loader requester){
        if (mHttpRequesters.contains(requester)){
            requester.cancel();
        }
    }


    /**
     * 解散RequesterGroup对象：取消该组所有网络请求
     */
    public void dismiss(){
        for (Loader httpRequester:
             mHttpRequesters) {
            httpRequester.cancel();
            mHttpRequesters.remove(httpRequester);
        }
    }
}
