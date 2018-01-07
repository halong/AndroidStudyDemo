package com.example.halong.myapplication.ui.ActionBarDrawerToggle;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.halong.myapplication.R;
import com.orhanobut.logger.Logger;

public class ActionBarDrawerToggleActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

        //设置状态栏透明  另在布局文件中需要设置fitsSystemWindows="ture"属性
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.activity_action_bar_drawer_toggle);

//        setSystemBarTransparent();
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer1 = (DrawerLayout) findViewById(R.id.drawer1);

        //ActionBarDrawerToggle 要与Toolbar DrawerLayout配合使用
        // 需要设置style/Theme.AppCompat.Light.NoActionBar
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer1, mToolbar, 0, 0);
        mDrawer1.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();//同步
    }

    private void setSystemBarTransparent() {
        Logger.d(Build.VERSION.SDK_INT);
        Logger.d(Build.VERSION_CODES.LOLLIPOP);
        Logger.d(Build.VERSION_CODES.KITKAT);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer1.isDrawerOpen(GravityCompat.START)) {
            mDrawer1.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
