package com.example.halong.myapplication.bean;

/**
 * Created by halong on 2017/8/4.
 */

public class User {
    public String name;
    public int age;
    public double height;
    public boolean isMale;

    public User() {
    }

    public User(String name ,int age,double height,boolean isMale){
        this.name=name;
        this.age=age;
        this.height=height;
        this.isMale=isMale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
