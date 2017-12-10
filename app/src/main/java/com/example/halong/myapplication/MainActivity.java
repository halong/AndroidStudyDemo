package com.example.halong.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.halong.myapplication.ui.ActionBarDrawerToggle.ActionBarDrawerToggleActivity;
import com.example.halong.myapplication.ui.Animation.AnimationActivity;
import com.example.halong.myapplication.BroadcastReceiver.BroadcastActivity;
import com.example.halong.myapplication.ui.CardView.CardViewActivity;
import com.example.halong.myapplication.ContentProvider.ContentActivity;
import com.example.halong.myapplication.Dagger2.DaggerActivity;
import com.example.halong.myapplication.ui.Dialog.DialogActivity;
import com.example.halong.myapplication.ui.EditText.EditTextActivity;
import com.example.halong.myapplication.EventBus.EventbusActivity;
import com.example.halong.myapplication.ui.Fragment.FragmentActivity;
import com.example.halong.myapplication.data.database.GreenDao.GreenDaoActivity;
import com.example.halong.myapplication.Gson.GsonActivity;
import com.example.halong.myapplication.data.network.NanoHTTPD.NanohttpdActivity;
import com.example.halong.myapplication.ui.Notification.NotificationActivity;
import com.example.halong.myapplication.data.network.OkHttpUtils.OkHttpUtilsActivity;
import com.example.halong.myapplication.data.network.Okhttp.OkhttpActivity;
import com.example.halong.myapplication.ui.RecyclerView.RecyclerViewDemoActivity;
import com.example.halong.myapplication.data.network.Retrofit.RetrofitActivity;
import com.example.halong.myapplication.data.database.SQLite.DatabaseActivity;
import com.example.halong.myapplication.Service.ServiceActivity;
import com.example.halong.myapplication.ui.TabLayout.TabLayoutActivity;
import com.example.halong.myapplication.ui.TextInputLayout.TextInputLayoutActivity;
import com.example.halong.myapplication.ui.Toolbar.ToolbarActivity;
import com.example.halong.myapplication.pulltorefresh.PulltorefreshActivity;

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

        mButton1.setText("Fragment");
        mButton2.setText("Broadcast");
        mButton3.setText("Service");
        mButton4.setText("Content");
        mButton5.setText("Pulltorefresh");
        mButton6.setText("EditText");
        mButton7.setText("Eventbus");
        mButton8.setText("GreenDao");
        mButton9.setText("Retrofit");
        mButton10.setText("Notification");
        mButton11.setText("TabLayout");
        mButton12.setText("SQLite");
        mButton13.setText("");
        mButton14.setText("Dialog");
        mButton15.setText("Gson");
        mButton16.setText("RecyclerView");
        mButton17.setText("CardView");
        mButton18.setText("Animation");
        mButton19.setText("Dagger2");
        mButton20.setText("NanoHTTPD");
        mButton21.setText("TextInputLayout");
        mButton22.setText("ActionBarDrawerToggle");
        mButton23.setText("OkHttpUtils");
        mButton24.setText("Toolbar");
        mButton25.setText("OkHttp");

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
                startActivity(new Intent(this, EditTextActivity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(this, EventbusActivity.class));
                break;
            case R.id.button8:
                startActivity(new Intent(this, GreenDaoActivity.class));
                break;
            case R.id.button9:
                startActivity(new Intent(this, RetrofitActivity.class));
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
                break;
            case R.id.button14:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.button15:
                startActivity(new Intent(this, GsonActivity.class));
                break;
            case R.id.button16:
                startActivity(new Intent(this, RecyclerViewDemoActivity.class));
                break;
            case R.id.button17:
                startActivity(new Intent(this, CardViewActivity.class));
                break;
            case R.id.button18:
                startActivity(new Intent(this, AnimationActivity.class));
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
                startActivity(new Intent(this, ToolbarActivity.class));
                break;
            case R.id.button25:
                startActivity(new Intent(this, OkhttpActivity.class));
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
