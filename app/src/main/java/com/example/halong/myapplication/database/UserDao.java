package com.example.halong.myapplication.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.halong.myapplication.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by halong on 2017/8/12.
 */

public class UserDao {
    SQLiteDatabase sqLiteDatabase;

    public UserDao(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.name);
        values.put("age", user.age);
        values.put("height", user.height);
        values.put("isMale", user.isMale);
        sqLiteDatabase.insert("users", "id", values);
    }

    public void updateUserByName(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.name);
        values.put("age", user.age);
        values.put("height", user.height);
        values.put("isMale", user.isMale);
        sqLiteDatabase.update("users", values, "name=?", new String[]{user.name});
    }


    public void deleteUserByName(String name) {
        sqLiteDatabase.delete("users", "name=?", new String[]{name});
    }

    public User getUserByPosition(int position) {
//        position++;
        //SQLite  primary key id 以1开始
        // database不像list删除一条信息后会顺序跟上
        // database像一张表格删了一条信息后，该处为空，后续加的信息不会补上
        //id对于position无意义

        User user = new User();
        Cursor cursor = sqLiteDatabase.query("users", new String[]{"id", "name", "age", "height", "isMale"}
                , "id=?", new String[]{position + ""}, null, null, "name asc");

        if (cursor.moveToNext()) {
            user.name = cursor.getString(cursor.getColumnIndex("name"));
            user.age = cursor.getInt(cursor.getColumnIndex("age"));
            user.height = cursor.getDouble(cursor.getColumnIndex("height"));
            if (cursor.getInt(cursor.getColumnIndex("isMale")) == 0) {
                user.isMale = false;
            } else {
                user.isMale = true;
            }
        }
        cursor.close();
        return user;
    }


    public List<User> getUsersByName(String name) {
        List<User> users = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query("users", new String[]{"name", "age", "height", "isMale"},
                "name=?", new String[]{name}, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.name = cursor.getString(cursor.getColumnIndex("name"));
            user.age = cursor.getInt(cursor.getColumnIndex("age"));
            user.height = cursor.getDouble(cursor.getColumnIndex("height"));

            //boolean转换
            if (cursor.getInt(cursor.getColumnIndex("isMale")) == 0) {
                user.isMale = false;
            } else {
                user.isMale = true;
            }
            users.add(user);
        }
        cursor.close();
        return users;
    }

    public List<User> getUsersByAge(int age) {
        List<User> users = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query("users", new String[]{"name", "age", "height", "isMale"},
                "age=?", new String[]{age + ""}, null, null, null);

        while (cursor.moveToNext()) {
            User user = new User();
            user.name = cursor.getString(cursor.getColumnIndex("name"));
            user.age = cursor.getInt(cursor.getColumnIndex("age"));
            user.height = cursor.getDouble(cursor.getColumnIndex("height"));

            if (cursor.getInt(cursor.getColumnIndex("isMale")) == 0) {
                user.isMale = false;
            } else {
                user.isMale = true;
            }
            users.add(user);
        }

        cursor.close();
        return users;
    }
}
