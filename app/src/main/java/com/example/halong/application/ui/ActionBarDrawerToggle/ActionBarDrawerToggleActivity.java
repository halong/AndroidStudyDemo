package com.example.halong.application.ui.ActionBarDrawerToggle;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.halong.application.R;

import java.util.ArrayList;
import java.util.List;

public class ActionBarDrawerToggleActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawer1;
    private ViewPager mViewPager;
    List<Fragment> fragments;
    private TextView mIndicator;
    private ViewGroup.MarginLayoutParams layoutParams;

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

        //ActionBarDrawerToggle 与Toolbar, DrawerLayout配合使用
        // 需要设置style/Theme.AppCompat.Light.NoActionBar
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer1, mToolbar, 0, 0);
        mDrawer1.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();//同步

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        fragments = new ArrayList<>();
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();
        fragments.add(firstFragment);
        fragments.add(secondFragment);
        fragments.add(thirdFragment);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        mIndicator = (TextView) findViewById(R.id.indicator);
        layoutParams= (ViewGroup.MarginLayoutParams) mIndicator.getLayoutParams();
        layoutParams.width=getResources().getDisplayMetrics().widthPixels/3;
        mIndicator.setLayoutParams(layoutParams);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                layoutParams.leftMargin=position*getResources().getDisplayMetrics().widthPixels/3+positionOffsetPixels/3;
                mIndicator.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setSystemBarTransparent() {
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
