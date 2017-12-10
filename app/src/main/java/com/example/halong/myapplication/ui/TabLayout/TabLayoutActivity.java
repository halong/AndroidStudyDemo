package com.example.halong.myapplication.ui.TabLayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halong.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    private ViewPager mViewpager;
    private TabLayout mTablayout;
    private Fragment fragment1, fragment2, fragment3;
    private List<Fragment> fragments;
    private List<View> views;
    private View view0, view1, view2;
    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        initData();
        initView();
    }

    private void initData() {
        fragment1 = new Blank11Fragment();
        fragment2 = new Blank12Fragment();
        fragment3 = new Blank13Fragment();

        fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);

        layoutInflater = LayoutInflater.from(this);

        view0 = layoutInflater.inflate(R.layout.item_tab, null, false);
        view1 = layoutInflater.inflate(R.layout.item_tab, null, false);
        view2 = layoutInflater.inflate(R.layout.item_tab, null, false);

        ((ImageView) (view0.findViewById(R.id.image))).setImageResource(R.mipmap.ic_launcher_round);
        ((ImageView) (view1.findViewById(R.id.image))).setImageResource(R.mipmap.ic_launcher);
        ((ImageView) (view2.findViewById(R.id.image))).setImageResource(R.mipmap.ic_launcher);

        ((TextView) (view0.findViewById(R.id.text))).setText("fragment0");
        ((TextView) (view1.findViewById(R.id.text))).setText("fragment1");
        ((TextView) (view2.findViewById(R.id.text))).setText("fragment2");

        views = new ArrayList<>();
        views.add(view0);
        views.add(view1);
        views.add(view2);
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);

        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        mTablayout.setupWithViewPager(mViewpager);

        mTablayout.getTabAt(0).setCustomView(view0);
        mTablayout.getTabAt(1).setCustomView(view1);
        mTablayout.getTabAt(2).setCustomView(view2);

        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((ImageView) (tab.getCustomView().findViewById(R.id.image))).setImageResource(R.mipmap.ic_launcher_round);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((ImageView) (tab.getCustomView().findViewById(R.id.image))).setImageResource(R.mipmap.ic_launcher);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

}
