package com.example.halong.myapplication.data.network.Okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.File;
import java.util.Map;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by halong on 2017/12/9.
 */

public class MyOkHttpUtil {
    private Handler handler=new Handler(Looper.getMainLooper());
    private MyOkHttpUtil() {
    }

    private static class Instance {
        public static OkHttpClient okHttpClient = new OkHttpClient();
    }

    public static OkHttpClient getOkHttpClient() {
        return Instance.okHttpClient;
    }

    //不带参数的GET请求
    public static void get(String url, Callback callback) {
        get(url, null, callback);
    }

    //带参数的GET请求
    public static void get(String url, Map<String, String> parmas, Callback callback) {
        if (parmas != null) {//将参数放到url
            url += "?";
            for (String key : parmas.keySet()) {
                url += key + "=" + parmas.get(key) + "&";
            }
            url = url.substring(0, url.length() - 1);
        }

        Request request = new Request.Builder().get().url(url).build();
        getOkHttpClient().newCall(request).enqueue(callback);
    }


    //POST上传参数
    public static void post(String url, Map<String, String> parmas, Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        if (parmas != null) {
            for (String key : parmas.keySet()) {
                builder.add(key, parmas.get(key));
            }
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder().post(body).url(url).build();
        getOkHttpClient().newCall(request).enqueue(callback);
    }

    //Post上传字符串
    public static void post(String url, String string, Callback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain;chart=utf-8"), string);
        Request request = new Request.Builder().post(body).url(url).build();
        getOkHttpClient().newCall(request).enqueue(callback);
    }

    //POST上传文件
    public static void post(String url, File file, Callback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        Request request = new Request.Builder().post(body).url(url).build();
        getOkHttpClient().newCall(request).enqueue(callback);
    }

    //POST上传表单
    public static void post(String url, Map<String, String> params, Map<String, Map<String, File>> files, Callback callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (params != null && params.size() > 0) {
            for (String key : params.keySet())
                builder.addFormDataPart(key, params.get(key));
        }

        if (files != null && files.size() > 0) {
            for (String key : files.keySet()) {
                Map<String, File> file = files.get(key);
                for (String k : file.keySet())
                    builder.addFormDataPart(key, k, RequestBody.create(MediaType.parse("application/octet-stream"), file.get(k)));
            }
        }

        RequestBody body = builder.setType(MultipartBody.FORM).build();
        Request request = new Request.Builder().post(body).url(url).build();
        getOkHttpClient().newCall(request).enqueue(callback);
    }
}
