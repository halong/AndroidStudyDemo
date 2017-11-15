package com.example.halong.myapplication.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.halong.myapplication.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    MyService myService;
    /**
     * button
     */
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();

//        startService(new Intent(this,MyService.class));
        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                myService = ((MyService.MyBinder) iBinder).getService();
                Log.d("==>", "getService");
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        bindService(new Intent(this, MyService.class), conn, BIND_AUTO_CREATE);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        myService.test();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                myService.test();
                break;
        }
    }
}
