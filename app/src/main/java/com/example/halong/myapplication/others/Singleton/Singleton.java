package com.example.halong.myapplication.others.Singleton;

import com.orhanobut.logger.Logger;

/**
 * Created by halong on 2017/12/27.
 * <p>
 * 使用enum实现单例-->程序运行中只实例化一次的类
 * <p>
 * 传统方法->两私一公（私有构造方法、私有静态实例(懒实例化-<静态内部类> 或者直接实例化)、公开的静态获取方法）
 * 线程安全问题->双重检查锁volitate synchronized
 * 反射破坏单例->无法解决
 */


public enum Singleton {
    INSTANCE;//枚举单例 构造方法只能调用一次，解决反射破坏单例

    private Singleton() {
        Logger.d("Singleton");
    }

    //============以下是一般属性和方法==============//

    public String test() {
        return this.name();
    }
}
