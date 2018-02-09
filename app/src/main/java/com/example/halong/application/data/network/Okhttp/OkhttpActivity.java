package com.example.halong.application.data.network.Okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.application.R;
import com.example.halong.application.data.network.NanoHTTPD.TestNanoHTTPD;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText;
    private ImageView mIamge;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;

    /**
     * 测试->POST上传文件
     */
    private Button mButton5;
    /**
     * 测试->POST上传表单
     */
    private Button mButton6;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
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
        mText = (TextView) findViewById(R.id.text);
        mIamge = (ImageView) findViewById(R.id.iamge);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mButton5 = (Button) findViewById(R.id.button5);
        mButton5.setOnClickListener(this);
        mButton6 = (Button) findViewById(R.id.button6);
        mButton6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                OkHttpClient okHttpClient = new OkHttpClient();

                Request request = new Request.Builder().url("https://www.uc123.com/").build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        final String msg = e.getMessage();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mText.setText(msg);
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String s = response.body().string();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mText.setText(s);
                            }
                        });
                    }
                });

                break;
            case R.id.button2:
                mText.setText("加载中。。。");

                StringDownloader requester = new StringDownloader.Builder()
                        .url("https://www.uc123.com/")
                        .callback(new StringDownloader.StringCallback() {
                            @Override
                            public void onFailure(String msg) {
                                mText.setText(msg);
                            }

                            @Override
                            public void onResponse(String data) {
                                mText.setText(data);
                            }

                        })
                        .build();

                requester.execute();

                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;

            case R.id.button5:
                break;

            case R.id.button6:
                break;
        }
    }
}
