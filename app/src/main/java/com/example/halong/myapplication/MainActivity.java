package com.example.halong.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.halong.myapplication.ActionBarDrawerToggle.ActionBarDrawerToggleActivity;
import com.example.halong.myapplication.CardView.CardViewDemoActivity;
import com.example.halong.myapplication.Dialog.DialogActivity;
import com.example.halong.myapplication.DrawerLayout.DrawerLayoutActivity;
import com.example.halong.myapplication.Gson.GsonDemoActivity;
import com.example.halong.myapplication.Notification.NotificationActivity;
import com.example.halong.myapplication.OkHttpUtils.OkHttpUtilsActivity;
import com.example.halong.myapplication.RecyclerView.RecyclerViewDemoActivity;
import com.example.halong.myapplication.TabLayout.TabLayoutActivity;
import com.example.halong.myapplication.TabViewPager.TabViewPagerActivity;
import com.example.halong.myapplication.TextInputLayout.TextInputLayoutActivity;
import com.example.halong.myapplication.broadcast.BroadcastActivity;
import com.example.halong.myapplication.content.ContentActivity;
import com.example.halong.myapplication.dagger2.DaggerActivity;
import com.example.halong.myapplication.database.DatabaseActivity;
import com.example.halong.myapplication.eventbus.EventbusActivity;
import com.example.halong.myapplication.fragment.FragmentActivity;
import com.example.halong.myapplication.OkHttp.OkhttpActivity;
import com.example.halong.myapplication.mvp.MvpActivity;
import com.example.halong.myapplication.nanohttpd.NanohttpdActivity;
import com.example.halong.myapplication.pulltorefresh.PulltorefreshActivity;
import com.example.halong.myapplication.service.ServiceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button4)
    Button mButton4;
    @BindView(R.id.button5)
    Button mButton5;
    @BindView(R.id.button6)
    Button mButton6;
    @BindView(R.id.button7)
    Button mButton7;
    @BindView(R.id.button8)
    Button mButton8;
    @BindView(R.id.button9)
    Button mButton9;
    @BindView(R.id.button10)
    Button mButton10;
    @BindView(R.id.button11)
    Button mButton11;
    @BindView(R.id.button12)
    Button mButton12;
    @BindView(R.id.button13)
    Button mButton13;
    @BindView(R.id.button14)
    Button mButton14;
    @BindView(R.id.button15)
    Button mButton15;
    @BindView(R.id.button16)
    Button mButton16;
    @BindView(R.id.button17)
    Button mButton17;
    @BindView(R.id.button18)
    Button mButton18;
    @BindView(R.id.button19)
    Button mButton19;
    @BindView(R.id.button20)
    Button mButton20;
    @BindView(R.id.button21)
    Button mButton21;
    @BindView(R.id.button22)
    Button mButton22;
    @BindView(R.id.button23)
    Button mButton23;
    @BindView(R.id.button24)
    Button mButton24;
    @BindView(R.id.button25)
    Button mButton25;
    @BindView(R.id.button26)
    Button mButton26;
    @BindView(R.id.button27)
    Button mButton27;
    @BindView(R.id.button28)
    Button mButton28;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        float density=getResources().getDisplayMetrics().density;
        float widthPixels=getResources().getDisplayMetrics().widthPixels;

        Log.d("==>",(int)(widthPixels/density+0.5f)+"");

        mButton1.setText("Fragment");
        mButton2.setText("Broadcast");
        mButton3.setText("Service");
        mButton4.setText("Content");
        mButton5.setText("Pulltorefresh");
        mButton6.setText("MvpActivity");
        mButton7.setText("Eventbus");
        mButton8.setText("DrawerLayout");
        mButton9.setText("TabViewPager");
        mButton10.setText("Notification");
        mButton11.setText("TabLayout");
        mButton12.setText("SQLite");
        mButton13.setText("OkhttpActivity");
        mButton14.setText("DialogActivity");
        mButton15.setText("GsonDemoActivity");
        mButton16.setText("RecyclerViewDemoActivity");
        mButton17.setText("CardViewDemoActivity");
        mButton18.setText("AnimationDemoActivity");
        mButton19.setText("DaggerActivity");
        mButton20.setText("NanohttpdActivity");
        mButton21.setText("TextInputLayoutActivity");
        mButton22.setText("ActionBarDrawerToggleActivity");
        mButton23.setText("OkHttpUtilsActivity");


    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18, R.id.button19, R.id.button20, R.id.button21, R.id.button22, R.id.button23, R.id.button24, R.id.button25, R.id.button26, R.id.button27, R.id.button28})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this, FragmentActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, BroadcastActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, ContentActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(this, PulltorefreshActivity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(this, MvpActivity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(this, EventbusActivity.class));
                break;
            case R.id.button8:
                startActivity(new Intent(this, DrawerLayoutActivity.class));
                break;
            case R.id.button9:
                startActivity(new Intent(this, TabViewPagerActivity.class));
                break;
            case R.id.button10:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.button11:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
            case R.id.button12:
                startActivity(new Intent(this, DatabaseActivity.class));
                break;
            case R.id.button13:
                startActivity(new Intent(this, OkhttpActivity.class));
                break;
            case R.id.button14:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.button15:
                startActivity(new Intent(this, GsonDemoActivity.class));
                break;
            case R.id.button16:
                startActivity(new Intent(this, RecyclerViewDemoActivity.class));
                break;
            case R.id.button17:
                startActivity(new Intent(this, CardViewDemoActivity.class));
                break;
            case R.id.button18:
//                startActivity(new Intent(this, AnimationDemoActivity.class));
                break;
            case R.id.button19:
                startActivity(new Intent(this, DaggerActivity.class));
                break;
            case R.id.button20:
                startActivity(new Intent(this, NanohttpdActivity.class));
                break;
            case R.id.button21:
                startActivity(new Intent(this, TextInputLayoutActivity.class));
                break;
            case R.id.button22:
                startActivity(new Intent(this, ActionBarDrawerToggleActivity.class));
                break;
            case R.id.button23:
                startActivity(new Intent(this, OkHttpUtilsActivity.class));
                break;
            case R.id.button24:
                break;
            case R.id.button25:
                break;
            case R.id.button26:
                break;
            case R.id.button27:
                break;
            case R.id.button28:
                break;
        }
    }
}
