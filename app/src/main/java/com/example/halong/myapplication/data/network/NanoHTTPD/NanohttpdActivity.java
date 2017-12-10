package com.example.halong.myapplication.data.network.NanoHTTPD;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

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
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }
}
