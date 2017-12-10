package com.example.halong.myapplication.data.network.NanoHTTPD;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.myapplication.R;

public class NanohttpdActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mText;
    private ImageView mImage;
    /**
     * 测试DownloadService
     */
    private Button mBtn1;
    /**
     * 测试UploadService
     */
    private Button mBtn2;
    /**
     * 测试Form
     */
    private Button mBtn3;
    /**
     * 测试Get请求下载文件
     */
    private Button mBtn4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanohttpd);
        initView();
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
