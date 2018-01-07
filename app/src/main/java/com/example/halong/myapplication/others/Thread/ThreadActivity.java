package com.example.halong.myapplication.others.Thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.halong.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadActivity extends AppCompatActivity {

    @BindView(R.id.text)
    TextView mText;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ButterKnife.bind(this);

        mText.setText("kkkk");

        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                mText.setText(msg.what+"");
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(5);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(555555);
            }
        }).start();
    }

}
