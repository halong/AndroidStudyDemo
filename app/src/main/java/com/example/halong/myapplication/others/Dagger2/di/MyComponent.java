package com.example.halong.myapplication.others.Dagger2.di;

import com.example.halong.myapplication.others.Dagger2.DaggerActivity;

import dagger.Component;

/**
 * Created by halong on 2017/9/4.
 */
@Component(modules = {MyModule.class})
public interface MyComponent {
     void inject(DaggerActivity daggerActivity);
}
