package com.example.halong.myapplication.others.Singleton;

/**
 * Created by halong on 2017/12/27.
 */

public class Singleton3 {
    private static Singleton3 singleton3;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (singleton3 == null) {
            synchronized (Singleton3.class) {
                singleton3 = new Singleton3();
            }
        }
        return singleton3;
    }

    public String test() {
        return this.getClass().getName();
    }
}
