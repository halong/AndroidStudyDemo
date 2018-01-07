package com.example.halong.myapplication.data.network.RxJavaRetrofit;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import com.example.halong.myapplication.data.network.NanoHTTPD.TestNanoHTTPD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxjavaRetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private ProgressBar mProgress;

    private Retrofit retrofit;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_retrofit);
        initView();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sojson.com/open/api/weather/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    private void initView() {
        mText = (TextView) findViewById(R.id.text);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton1.setText("下载JSON");
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton2.setText("下载文件");
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton3.setText("上传文件");
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mProgress = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            TestNanoHTTPD.getDefault().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        TestNanoHTTPD.getDefault().stop();
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1://下载JSON
                apiService
                        .get(TestNanoHTTPD.URL)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(User user) {
                                mText.setText(user.getName());
                            }
                        });
                break;


            case R.id.button2://下载文件-> 进度  控制（开始 暂停 继续 取消）
                Map<String,String> params=new HashMap<String,String> ();
                apiService.post(TestNanoHTTPD.URL,params)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    mText.setText(responseBody.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            case R.id.button3://上传文件->进度显示，暂停，继续，取消
                File file3=new File(Environment.getExternalStorageDirectory(),"ii.txt");
                try {
                    OutputStream outputStream=new FileOutputStream(file3);
                    outputStream.write("我是中文文档lalala".getBytes());
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                RequestBody body=MyRequestBody.create(MediaType.parse("text/plain;charset=utf-8"),file3);

                apiService.post(TestNanoHTTPD.URL,body)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    mText.setText(responseBody.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                break;
            case R.id.button4:
                break;
        }
    }


    interface ApiService {
        @GET
        Observable<User> get(@Url String url);

        @POST
        Observable<ResponseBody> post(@Url String url, @QueryMap Map<String,String> params);

        @POST
        Observable<ResponseBody> post(@Url String url, @Body RequestBody body);
    }
}
