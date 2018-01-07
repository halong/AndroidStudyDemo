package com.example.halong.myapplication.data.network.Retrofit;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import com.example.halong.myapplication.data.network.NanoHTTPD.TestNanoHTTPD;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private TextView mText;
    private ImageView mIamge;

    private ApiService apiService;
    private Retrofit retrofit;
    /**
     * 测试Retrofit+RxAndroid
     */
    private Button mButton5;
    /**
     * 测试Retrofit的POST
     */
    private Button mButton6;
    /**
     * 测试Retrofit:GET请求json
     */
    private Button mButton7;
    /**
     * 测试Retrofit:getWeather
     */
    private Button mButton8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initView();

        retrofit = new Retrofit.Builder()
                .baseUrl(TestNanoHTTPD.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

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


    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mText = (TextView) findViewById(R.id.text);
        mIamge = (ImageView) findViewById(R.id.iamge);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton5.setOnClickListener(this);
        mButton6 = (Button) findViewById(R.id.button6);
        mButton6.setOnClickListener(this);
        mButton7 = (Button) findViewById(R.id.button7);
        mButton7.setOnClickListener(this);
        mButton8 = (Button) findViewById(R.id.button8);
        mButton8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1://测试GET请求 下载JSON
                apiService.get().enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        mText.setText(new Gson().toJson(response.body()));
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        mText.setText(t.getMessage());
                    }
                });
                break;

            case R.id.button2://测试POST请求 下载文件
                Map<String, String> params2 = new HashMap<>();
                apiService.post(params2).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            mText.setText(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        mText.setText(t.getMessage());
                    }
                });
                break;


            case R.id.button3: //测试Retrofit的POST 上传文件
                final File file3 = new File(Environment.getExternalStorageDirectory(), "uu.txt");
                try {
                    OutputStream outputStream3 = new FileOutputStream(file3);
                    outputStream3.write("我是文本文件我是文本文件我是文本文件我是文本文件".getBytes());
                    outputStream3.flush();
                    outputStream3.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                RequestBody body3 = RequestBody.create(MediaType.parse("plain/text"), file3);
                apiService.post(body3)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    mText.setText(response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                mText.setText(t.getMessage());
                            }
                        });
                break;

            case R.id.button4://测试retrofit的POST Form
                Map<String, Map<String, File>> params4 = new HashMap<>();
                Map<String, File> f = new HashMap<>();
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "sahd.txt");
                try {
                    OutputStream outputStream = new FileOutputStream(file);
                    outputStream.write("我是中文字符lalalala".getBytes());
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                f.put("uu.txt", file);
                params4.put("form", f);
                apiService.postForm(params4)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    mText.setText(response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                mText.setText(t.getMessage());
                            }
                        });
                break;
            case R.id.button5://测试Retrofit的GET请求
                Map<String, String> params5 = new HashMap<>();
                params5.put("params", "中文请求");
                apiService.get(params5)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    mText.setText(response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                mText.setText(t.getMessage());
                            }
                        });
                break;

            case R.id.button6:
                break;

        }
    }


    interface ApiService {
        @GET("/")
        Call<User> get();

        @GET("/")
        Call<ResponseBody> get(@QueryMap Map<String, String> params);

        @FormUrlEncoded//POST需要编码
        @POST("/")
        Call<ResponseBody> post(@FieldMap Map<String, String> params);

        @POST("/")
        Call<ResponseBody> post(@Body RequestBody body);

        //@Streaming 下载文件需要加上 否则会直接加载到内存
        @Multipart
        @POST("/")
        Call<ResponseBody> postForm(@PartMap Map<String, Map<String, File>> files);
    }
}
