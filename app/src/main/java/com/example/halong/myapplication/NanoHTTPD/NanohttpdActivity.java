package com.example.halong.myapplication.NanoHTTPD;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NanohttpdActivity extends AppCompatActivity implements View.OnClickListener {
    private MyNanohttpd myNanohttpd;
    private TextView mTv1;

    private String text1 = "";
    private Button mBtn1;
    /**
     * 开启
     */
    private Button mBtn2;
    /**
     * 停止
     */
    private Button mBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanohttpd);


        initView();
        setupView();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);


    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Intent intent) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String date = dateFormat.format(new Date());
        text1 += date + "  " + intent.getAction() + "\n";
        mTv1.setText(text1);
    }


    private void initView() {
        mTv1 = (TextView) findViewById(R.id.tv1);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
        mBtn1.setOnClickListener(this);
    }

    private void setupView() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn2:
                try {
                    myNanohttpd = new MyNanohttpd();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn3:
                if (myNanohttpd != null) {
                    myNanohttpd.stop();
                    myNanohttpd = null;
                }
                break;
            case R.id.btn1:
                String url = "http://localhost:8088?request=hello";
//                String url = "http://localhost:8088";
//                String url="https://www.uc123.com/";
                RequestBody body = new FormBody.Builder().add("wd", "hello").build();
                Request request = new Request.Builder().url(url).build();
                Call call = new OkHttpClient().newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new Intent("response:" + e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        EventBus.getDefault().post(new Intent("response:" + response.body().string()));
                    }
                });
                break;
        }
    }
}
