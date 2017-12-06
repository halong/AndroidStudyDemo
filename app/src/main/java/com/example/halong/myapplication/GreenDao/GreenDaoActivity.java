package com.example.halong.myapplication.GreenDao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.halong.myapplication.R;

import java.util.List;


public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mTvShow;
    /**
     * insert
     */
    private Button mBtnInsert;
    /**
     * query
     */
    private Button mBtnQuery;
    /**
     * update
     */
    private Button mBtnUpdate;
    /**
     * delete
     */
    private Button mBtnDelete;

    private String showText = "";

    private UserDao userDao = GreenDaoUtil.getUserDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        initView();
    }


    private void initView() {
        mTvShow = (TextView) findViewById(R.id.tv_show);
        mBtnInsert = (Button) findViewById(R.id.btn_insert);
        mBtnInsert.setOnClickListener(this);
        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mBtnQuery.setOnClickListener(this);
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mBtnUpdate.setOnClickListener(this);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                User user = new User(null, "张三", 19);
                userDao.insert(user);
                showText += " userDao.insert(user);\n";
                break;

            case R.id.btn_query:
                List<User> list = userDao.queryBuilder().build().list();
                for (User user1 : list) {
                    showText += "User:" + user1.getId() + "  " + user1.getName() + "  " + user1.getAge() + "\n";
                }
                break;

            case R.id.btn_update:
                List<User> users = userDao.queryBuilder().where(UserDao.Properties.Name.eq("张三")).build().list();
                User user1=null;
                if(users.size()>0){
                    user1=users.get(0);
                }
                if(user1!=null){
                    user1.setName("李四");
                    userDao.update(user1);
                    showText+="user1.setName(\"李四\");\n";
                }
                break;
            case R.id.btn_delete:
                List<User> users2 = userDao.queryBuilder().where(UserDao.Properties.Name.eq("张三")).build().list();
                User user2=null;
                if(users2.size()>0){
                    user2=users2.get(0);
                }

               if (user2!=null){
                   userDao.delete(user2);
                   showText+="userDao.delete(user2);\n";
               }
                break;
        }
        mTvShow.setText(showText);
    }
}
