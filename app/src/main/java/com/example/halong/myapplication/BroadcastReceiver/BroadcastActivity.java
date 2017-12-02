package com.example.halong.myapplication.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.halong.myapplication.R;
import com.orhanobut.logger.Logger;

public class BroadcastActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private Button mButton2;
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        initData();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter=new IntentFilter();
        filter.addAction("hello");
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        super.onStop();
    }

    private void initData() {
        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "在activity注册的BroadcastReceiver", Toast.LENGTH_SHORT).show();
                Logger.d("在activity注册的BroadcastReceiver");
            }
        };
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                sendBroadcast(new Intent(this, MyReceiver.class));
                break;
            case R.id.button2:
                sendBroadcast(new Intent("hello"));
                break;
        }
    }
}
