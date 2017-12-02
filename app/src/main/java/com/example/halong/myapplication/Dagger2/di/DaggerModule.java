package com.example.halong.myapplication.Dagger2.di;

import com.example.halong.myapplication.Dagger2.mvp.DaggerView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by halong on 2017/9/4.
 */
@Module
public class DaggerModule {
    private final DaggerView mDaggerView;

    public DaggerModule(DaggerView daggerView){
        mDaggerView=daggerView;
    }

    @Provides
    DaggerView provideDaggerView(){
        return mDaggerView;
    }
}
