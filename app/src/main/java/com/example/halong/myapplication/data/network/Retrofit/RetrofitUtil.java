package com.example.halong.myapplication.data.network.Retrofit;

import com.example.halong.myapplication.data.network.NanoHTTPD.TestService;

import java.io.File;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by halong on 2017/12/10.
 */

public class RetrofitUtil {
    private ApiService apiService;

    public RetrofitUtil() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TestService.URL)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void get(String url, Map<String, String> params, Callback callback) {
        apiService.get(params).enqueue(callback);
    }

    public void post(String url, Map<String, String> params, Callback callback) {
        apiService.post(url, params).enqueue(callback);
    }


    interface ApiService {
        @GET("/")
            //path若不添加子路径，则加"/" ;静态添加，直接在/后面添加; 动态添加,先加{mpath},再在参数中使用@path添加
        Call get( @QueryMap Map<String, String> params);

        @FormUrlEncoded//POST需要编码
        @POST("{path}")
        Call post(@Path("path") String path, @FieldMap Map<String, String> params);

        @POST("{path}")
        Call post(@Path("path") String path, @Body String string);

        @POST("/")
        Call postForm(@PartMap Map<String,Map<String,File>> files);

    }


}
