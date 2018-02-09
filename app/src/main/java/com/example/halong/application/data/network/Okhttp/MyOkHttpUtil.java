package com.example.halong.application.data.network.Okhttp;

import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by halong on 2017/12/9.
 */

public class MyOkHttpUtil {
    private  Handler mHandler;
    private  OkHttpClient okHttpClient;
    private static MyOkHttpUtil myOkHttpUtil;


    //不带参数的GET请求
    public void get(String url, MyCallback callback) {
        get(url, null, callback);
    }

    //带参数的GET请求
    public void get(String url, Map<String, String> parmas, final MyCallback callback) {
        if (parmas != null) {//将参数放到url
            url += "?";
            for (String key : parmas.keySet()) {
                url += key + "=" + parmas.get(key) + "&";
            }
            url = url.substring(0, url.length() - 1);
        }

        Request request = new Request.Builder().get().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

            }
        });
    }


    //POST上传参数

    public  void post(String url, Map<String, String> parmas, Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        if (parmas != null) {
            for (String key : parmas.keySet()) {
                builder.add(key, parmas.get(key));
            }
        }

        RequestBody body = builder.build();
        Request request = new Request.Builder().post(body).url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    //Post上传字符串
    public  void post(String url, String string, Callback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain;chart=utf-8"), string);
        Request request = new Request.Builder().post(body).url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    //POST上传文件
    public  void post(String url, File file, Callback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        Request request = new Request.Builder().post(body).url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    //POST上传表单
    public  void post(String url, Map<String, String> params, Map<String, Map<String, File>> files, Callback callback) {
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
        okHttpClient.newCall(request).enqueue(callback);
    }


    public interface MyCallback{
         void onFailure(IOException e);
         void onResponse(Response response);
    }
}
