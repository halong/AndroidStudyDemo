package com.example.halong.myapplication.others.LiveBus;

/**
 * Created by halong on 2017/12/27.
 *
 * 使用enum实现单例-->程序运行中只实例化一次的类
 *
 * 传统方法->两私一公（私有构造方法、私有静态实例(懒实例化-<静态内部类> 或者直接实例化)、公开的静态获取方法）
 * 线程安全问题->双重检查锁volitate synchronized
 * 反射破坏单例->无法解决
 */


public enum Singleton {
    INSTANCE;//枚举单例 优点：构造方法只能调用一次，解决反射破坏单例
    // 缺点：程序运行开始就预先加载，而不是第一次调用时加载


    //============以下是一般属性和方法==============//
    private String s = "fndsbf";

    public void setS(String t) {
        s = t;
    }

    public String getS() {
        return s;
    }
}
