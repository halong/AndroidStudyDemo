package com.example.halong.myapplication.data.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.halong.myapplication.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        getSharedPreferences("test",MODE_PRIVATE);
    }
}
