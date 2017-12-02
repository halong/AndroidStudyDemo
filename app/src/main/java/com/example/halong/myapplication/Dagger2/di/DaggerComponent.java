package com.example.halong.myapplication.Dagger2.di;

import com.example.halong.myapplication.Dagger2.DaggerActivity;

import dagger.Component;

/**
 * Created by halong on 2017/9/4.
 */
@Component(modules = DaggerModule.class)
public interface DaggerComponent {
     void inject(DaggerActivity daggerActivity);
}
