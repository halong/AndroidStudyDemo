package com.example.halong.myapplication.nanohttpd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.halong.myapplication.OkHttp.OkHttpClientSingleton;
import com.example.halong.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class NanohttpdActivity extends AppCompatActivity {
    private MyNanohttpd myNanohttpd;
    private TextView mTv1;

    private String text1 = "";
    private EditText mEt1;
    private Button mBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanohttpd);

        initView();

        try {
            myNanohttpd = new MyNanohttpd();
            myNanohttpd.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Intent intent) {
        text1 += intent.getAction() + "\n";
        mTv1.setText(text1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myNanohttpd != null) {

            myNanohttpd.stop();
        }

        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        mTv1 = (TextView) findViewById(R.id.tv1);
        mEt1 = (EditText) findViewById(R.id.et1);
        mBtn1 = (Button) findViewById(R.id.btn1);

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url=mEt1.getText().toString();
                Request request=new Request.Builder().url(url).get().build();
                OkHttpClientSingleton.getOkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        EventBus.getDefault().post(new Intent("Client:"+e.getMessage()));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        EventBus.getDefault().post(new Intent("Client:"+response.body().string()));
                    }
                });
            }
        });
    }

}
