package com.example.halong.myapplication.ui.TempControlView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.halong.myapplication.R;

public class TempControlViewActivity extends AppCompatActivity {

    private TempControlView mTempControlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_control_view);
        initView();


    }

    private void initView() {
        mTempControlView = (TempControlView) findViewById(R.id.tempControlView);
    }
}
