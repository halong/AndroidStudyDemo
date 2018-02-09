package com.example.halong.application.ui.Glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.halong.application.R;

public class GlideActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.imageView);

        Glide.with(this)
                .load("https://img.alicdn.com/tfs/TB1AAHWh5qAXuNjy1XdXXaYcVXa-42-38.png")
                .into(mImageView);
    }
}
