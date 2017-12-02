package com.example.halong.myapplication.EditText;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.halong.myapplication.R;

public class EditTextActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        initView();
    }


    private void initView() {
        mEt1 = (EditText) findViewById(R.id.et1);
        mEt1.setOnClickListener(this);
        mEt1.setCursorVisible(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et1:
                mEt1.setCursorVisible(true);
                break;
        }
    }
}
