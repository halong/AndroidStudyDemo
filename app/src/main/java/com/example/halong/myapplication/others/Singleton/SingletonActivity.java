package com.example.halong.myapplication.others.Singleton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.halong.myapplication.R;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);

        //测试：enum单例
        Logger.d(Singleton.INSTANCE.test());
        Class class1 = Singleton.class;
        try {
            Constructor constructor2 = class1.getDeclaredConstructor(); //结果：构造器无法找到 因此不能再被实例化
            constructor2.setAccessible(true);
            Object object = constructor2.newInstance();
            Logger.d(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        //测试：内部类实现的单例
        Logger.d(Singleton2.getInstance().test());

        Class class2 = Singleton2.class;
        try {
            Constructor constructor2 = class2.getDeclaredConstructor();
            constructor2.setAccessible(true);
            Object object2 = constructor2.newInstance();
            Logger.d("Singleton2是否可以被两次实例化：" + (object2 != Singleton2.getInstance()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        //测试：双锁法实现的单例
        Logger.d(Singleton3.getInstance().test());

        Class class3 = Singleton3.class;
        try {
            Constructor constructor3 = class3.getDeclaredConstructor();
            constructor3.setAccessible(true);
            Object object3 = constructor3.newInstance();
            Logger.d("Singleton3是否可以被两次实例化：" + (object3 != Singleton3.getInstance()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
