package com.example.halong.myapplication.okhttputils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class OkHttpUtilsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn1;
    private TextView mTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_utils);

        initView();
        setupView();
    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        mTv1 = (TextView) findViewById(R.id.tv1);
    }

    private void setupView(){
        mBtn1.setText("OkHttpUtils.getString");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:

                String url = "http://www.csdn.net/";
                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("username", "hyman")
                        .addParams("password", "123")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                mTv1.setText(e.getMessage());

                            }

                            @Override
                            public void onResponse(String response) {

                                mTv1.setText(response);
                            }
                        });

                break;
        }
    }
}
