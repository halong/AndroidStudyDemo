package com.example.halong.myapplication.others.Singleton;

/**
 * Created by halong on 2017/12/27.
 */

public class Singleton2 {
    private Singleton2(){
    }

    private static class Inner{
        static Singleton2 singleton2=new Singleton2();
    }

    public static Singleton2 getInstance(){
        return Inner.singleton2;
    }

    public String test(){
        return this.getClass().getName();
    }
}
