package com.example.halong.myapplication.data.network.NanoHTTPD;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class NanohttpdActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mText;
    private ImageView mImage;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanohttpd);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            MyNanoHTTPD.getDefault().start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        MyNanoHTTPD.getDefault().stop();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onMessageEvent(EventMessage message) {
        switch (message.flag) {
            case EventMessage.text:

                break;
            case EventMessage.image:
                break;
        }

    }

    private void initView() {
        mText = (TextView) findViewById(R.id.text);
        mImage = (ImageView) findViewById(R.id.image);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mBtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                OkHttpUtils.get()
                        .url(MyNanoHTTPD.URL)
                        .addParams("param", "请求JSON")
                        .build()
                        .execute(new Callback<List<User>>() {
                            @Override
                            public List<User> parseNetworkResponse(Response response) throws IOException {
                                return new Gson().fromJson(response.body().string(), new TypeToken<List<User>>() {
                                }.getType());
                            }

                            @Override
                            public void onError(Request request, Exception e) {
                                mText.setText("失败");
                            }

                            @Override
                            public void onResponse(List<User> response) {
                                mText.setText(response.get(0).getName());
                            }
                        });
                break;
            case R.id.btn2:
                OkHttpUtils.get().url("http://localhost:8088?param=请求文件").build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "5656.jpg") {
                    @Override
                    public void inProgress(float progress) {
                        mText.setText(progress + "");
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        mText.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(File response) {
                        mImage.setImageURI(Uri.fromFile(response));
                    }
                });
                break;
            case R.id.btn3:
                String val = "请求JSON";
                try {
                    val = URLEncoder.encode(val, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                OkHttpUtils.post().url(MyNanoHTTPD.URL).addParams("param", val).build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        mText.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        mText.setText(response);
                    }
                });
                break;
            case R.id.btn4:
                File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "po.txt");
                try {
                    OutputStream outputStream = new FileOutputStream(f);
                    outputStream.write("sadfhjka中文".getBytes());
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                OkHttpUtils.post().url(MyNanoHTTPD.URL).addFile("form", "dshf.txt", f).build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        mText.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        mText.setText(response);
                    }
                });
                break;
        }
    }
}
