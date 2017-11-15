package com.example.halong.myapplication.ActionBarDrawerToggle;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.halong.myapplication.R;

public class ActionBarDrawerToggleActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_drawer_toggle);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        initView();

    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer1 = (DrawerLayout) findViewById(R.id.drawer1);

        setSupportActionBar(mToolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer1, mToolbar, 0, 0);
//        mDrawer1.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
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
