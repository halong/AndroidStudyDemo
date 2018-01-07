package com.example.halong.myapplication.others.LiveBus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.halong.myapplication.R;

public class LiveBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_bus);

        Singleton.INSTANCE.setS("dsf");
    }
}
