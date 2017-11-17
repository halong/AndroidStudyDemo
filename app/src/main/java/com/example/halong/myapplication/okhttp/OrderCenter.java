package com.example.halong.myapplication.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by halong on 2017/11/12.
 */

public class OrderCenter implements OkhttpActivity.OrderInterface {
    @Override
    public void getHtmlText() {
        Request request = new Request.Builder()
                .url("http://www.jianshu.com/p/ca65b59a6bf3")
                .get()
                .build();

        OkHttpClientSingleton.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                DataCenter.setText(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                DataCenter.setText(response.body().string());
            }
        });
    }
}
