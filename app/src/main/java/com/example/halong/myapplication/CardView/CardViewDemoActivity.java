package com.example.halong.myapplication.CardView;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.halong.myapplication.R;

public class CardViewDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_demo);

        initView();
    }

    private void initView() {
        mText1 = (TextView) findViewById(R.id.text1);
        mText1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                Toast.makeText(this, Build.VERSION.SDK_INT+"", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
