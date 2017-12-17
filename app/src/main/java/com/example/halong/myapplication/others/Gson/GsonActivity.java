package com.example.halong.myapplication.others.Gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GsonActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * to json
     */
    private Button mButton1;
    private TextView mText1;
    /**
     * to bean
     */
    private Button mButton2;
    private TextView mText2;

    private User user;
    private User user2;
    private List<User> users;
    private Gson gson;
    private String json;
    private String jsons;
    /**
     * to jsons
     */
    private Button mButton3;
    private TextView mText3;
    /**
     * to beans
     */
    private Button mButton4;
    private TextView mText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_demo);

        initData();
        initView();


    }

    private void initData() {
        user = new User("Lilei", 26, 170.2, true);
        user2 = new User("Wangmei", 25, 160.4, false);

        users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        gson = new Gson();
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mText1 = (TextView) findViewById(R.id.text1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mText2 = (TextView) findViewById(R.id.text2);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mText3 = (TextView) findViewById(R.id.text3);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
        mText4 = (TextView) findViewById(R.id.text4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                json = gson.toJson(user);
                mText1.setText(json);
                break;

            case R.id.button2:
                User user3 = gson.fromJson(json, User.class);
                mText2.setText(user3.name);
                break;

            case R.id.button3:
                jsons = gson.toJson(users);
                mText3.setText(jsons);
                break;

            case R.id.button4:
                List<User> us = gson.fromJson(jsons, new TypeToken<List<User>>(){}.getType());
                mText4.setText(us.get(1).name);
                break;
        }
    }
}
