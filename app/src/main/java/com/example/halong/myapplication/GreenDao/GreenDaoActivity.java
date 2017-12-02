package com.example.halong.myapplication.GreenDao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.halong.myapplication.R;

import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;
    /**
     * insert
     */
    private Button mBtn1;
    /**
     * query
     */
    private Button mBtn2;
    /**
     * update
     */
    private Button mBtn3;
    /**
     * delete
     */
    private Button mBtn4;

    private String text = "";

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        initView();

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();

    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mBtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                User user = new User(null, "zhangsan", "张三");
                userDao.insert(user);
                text += "insert a user\n";
                mTv.setText(text);
                break;
            case R.id.btn2:
                List<User> list = userDao.queryBuilder().where(UserDao.Properties.Username.eq("zhangsan")).build().list();
                text += "user1.getNickname:";
                for (User user1 : list) {
                    text += user1.getNickname() + "\n";
                }
                mTv.setText(text);
                break;
            case R.id.btn3:
                List<User> list3 = userDao.queryBuilder().where(UserDao.Properties.Username.eq("zhangsan")).build().list();
                for (User user1 : list3) {
                    user1.setNickname("王五");
                    userDao.update(user1);
                    text += "update user1\n";
                }
                mTv.setText(text);
                break;
            case R.id.btn4:
                List<User> list4 = userDao.queryBuilder().where(UserDao.Properties.Username.eq("zhangsan")).build().list();
                for (User user1 : list4) {
                    userDao.delete(user1);
                    text += "delete user1\n";
                }
                mTv.setText(text);
                break;
        }
    }
}
