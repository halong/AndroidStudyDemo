package com.example.halong.myapplication.data.database.SQLite;

/**
 * Created by halong on 2017/12/10.
 */

public class User {
    public String name;
    public int age;
    public double height;
    public boolean isMale;

    public User() {

    }

    public User(String name, int age, double height, boolean isMale) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.isMale = isMale;
    }
}
