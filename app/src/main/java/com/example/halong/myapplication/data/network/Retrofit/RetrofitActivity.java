package com.example.halong.myapplication.data.network.Retrofit;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import com.example.halong.myapplication.data.network.NanoHTTPD.MyNanoHTTPD;
import com.example.halong.myapplication.data.network.NanoHTTPD.TestService;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                .baseUrl(MyNanoHTTPD.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            MyNanoHTTPD.getDefault().start();
            TestService.getDefault().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        MyNanoHTTPD.getDefault().stop();
        TestService.getDefault().stop();
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
            case R.id.button1:
                Map<String, String> params = new HashMap<>();
                params.put("param", "请求JSON");

                apiService.get(params).enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        mText.setText(response.body().get(0).getName());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        mText.setText("错误");
                    }
                });
                break;
            case R.id.button2:
                Map<String, String> params2 = new HashMap<>();
                params2.put("param", "请求JSON");

                apiService.getResponseBody(params2).enqueue(new Callback<ResponseBody>() {
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
            case R.id.button3: //测试retrofit的POST请求
                Map<String, String> params3 = new HashMap<>();
                params3.put("param", "请求JSON");

                retrofit.newBuilder()
                        .baseUrl(TestService.URL) //更换baseUrl
                        .build()
                        .create(ApiService.class)
                        .post(params3)
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
            case R.id.button4://测试retrofit的PSOT form请求
                Map<String, Map<String, File>> params4 = new HashMap<>();
                Map<String, File> f = new HashMap<>();
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "sahd.txt");
                try {
                    OutputStream outputStream = new FileOutputStream(file);
                    outputStream.write("asjdg巴萨和地方".getBytes());
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                f.put("uu.txt", file);
                params4.put("form", f);
                retrofit.newBuilder()
                        .baseUrl(TestService.URL)
                        .build()
                        .create(ApiService.class)
                        .postForm(params4)
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
            case R.id.button5:
                Map<String, String> params5 = new HashMap<>();
                params5.put("param", "请求JSON");
                apiService.getWithRx(params5)  //得到Observable<ResponseBody>
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                mText.setText(e.getMessage());
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

            case R.id.button6:// post requestbody
                RequestBody body6 = RequestBody.create(MediaType.parse("text/plain"), "撒地方dsbf");
                retrofit.newBuilder()
                        .baseUrl(TestService.URL) //更换baseUrl
                        .build()
                        .create(ApiService.class)
                        .post(body6)
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
            case R.id.button7:
                retrofit.newBuilder()
                        .baseUrl("https://api.douban.com/v2/") //更换baseUrl
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                        .build()
                        .create(ApiService.class)
                        .getSearchBook("金瓶梅", null, 0, 1)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Book>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                mText.setText(e.getMessage());
                            }

                            @Override
                            public void onNext(Book book) {
                                mText.setText(book.getBooks().get(0).getAuthor().get(0));

                            }
                        });
                break;
            case R.id.button8:

                break;
        }
    }


    interface ApiService {
        @GET("/")
            //path若不添加子路径，则加"/" ;静态添加，直接在/后面添加; 动态添加,先加{mpath},再在参数中使用@path添加
        Call<List<User>> get(@QueryMap Map<String, String> params);

        @GET("/")
        Call<ResponseBody> getResponseBody(@QueryMap Map<String, String> params);

        @FormUrlEncoded//POST需要编码
        @POST("/")
        Call<ResponseBody> post(@FieldMap Map<String, String> params);

        @POST("/")
        Call<ResponseBody> post(@Body RequestBody body);

        //@Streaming 下载文件需要加上 否则会直接加载到内存
        @Multipart
        @POST("/")
        Call<ResponseBody> postForm(@PartMap Map<String, Map<String, File>> files);


        @GET("/")
        Observable<ResponseBody> getWithRx(@QueryMap Map<String, String> params);

        @GET("book/search")
        Observable<Book> getSearchBook(@Query("q") String name,
                                       @Query("tag") String tag,
                                       @Query("start") int start,
                                       @Query("count") int count);


    }
}
