package com.example.halong.myapplication.ui.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.halong.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivity extends AppCompatActivity{


    private List<Fragment> fragments = new ArrayList<>();
    private ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        addFragment();
//        addFragments();
    }


    /*动态加载*/
    private void addFragment(){
        BlankFragment blankFragment = new BlankFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.mFrameLayout,blankFragment);
        fragmentTransaction.replace(R.id.mFrameLayout,blankFragment);
        fragmentTransaction.commit();
    }



    private void addFragments() {
        Fragment fragment1 = new Fragment1();
        Fragment fragment2 = new Fragment2();
        Fragment fragment3 = new Fragment3();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);

        mVp = (ViewPager) findViewById(R.id.vp);
        mVp.setAdapter(new MyAdapter(getSupportFragmentManager(), fragments));
    }


    class MyAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments;

        public MyAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
            super(fragmentManager);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
