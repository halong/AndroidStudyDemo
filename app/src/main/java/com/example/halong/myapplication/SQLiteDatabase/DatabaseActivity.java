package com.example.halong.myapplication.SQLiteDatabase;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.halong.myapplication.R;
import com.example.halong.myapplication.DataCenter.bean.User;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mText;
    private UserDao userDao;
    private User user;
    private User user2;
    private TextView mText1;

    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        initData();
        initView();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    private void initData() {
        db = new MyDBHelper(this).getWritableDatabase();
        userDao = new UserDao(db);

        user = new User();
        user.name = "lilei";
        user.age = 13;
        user.height = 175.4;
        user.isMale = true;

        user2 = new User();
        user2.name = "wangmei";
        user2.age = 11;
        user2.height = 155.8;
        user2.isMale = false;
    }

    private void initView() {
        mText = (TextView) findViewById(R.id.text);
        mText.setOnClickListener(this);
        mText1 = (TextView) findViewById(R.id.text1);
        mText1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text:
//                userDao.deleteUserByName(user.name);
                userDao.addUser(user);
                Toast.makeText(this, "userDao.addUser(user)", Toast.LENGTH_SHORT).show();
                break;

            case R.id.text1:
                User user = userDao.getUserByPosition(20);
                if (user != null) {
                    mText1.setText(user.name + "\t"+user.age +"\t"+ user.height + "\t" + user.isMale);
                } else {
                    mText1.setText("null");
                }
                break;
        }
    }
}
