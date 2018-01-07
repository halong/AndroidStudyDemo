package com.example.halong.myapplication.others.Dagger2.di;

import com.example.halong.myapplication.others.Dagger2.mvp.MyModel;
import com.example.halong.myapplication.others.Dagger2.mvp.MyView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by halong on 2017/9/4.
 */
@Module
public class MyModule {
    private MyView mMyView;

    public MyModule(MyView myView){
        mMyView = myView;
    }

    @Provides
    MyView provideDaggerView(){
        return mMyView;
    }

    @Provides
    MyModel provideDaggerModel(){
        return new MyModel();
    }
}
