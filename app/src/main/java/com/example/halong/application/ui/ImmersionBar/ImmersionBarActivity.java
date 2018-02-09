package com.example.halong.application.ui.ImmersionBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.halong.application.R;
import com.gyf.barlibrary.ImmersionBar;

public class ImmersionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion_bar);

        ImmersionBar.with(this)
                .init(); //初始化，默认透明状态栏和黑色导航栏
    }

    @Override
    protected void onDestroy() {
        ImmersionBar.with(this).destroy(); //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态

        super.onDestroy();
    }
}
