package com.example.halong.myapplication.ContentProvider;

import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.halong.myapplication.R;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        ContentResolver contentResolver=getContentResolver();
    }
}
