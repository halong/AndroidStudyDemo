package com.example.halong.myapplication.dagger2.mvp;

import javax.inject.Inject;

/**
 * Created by halong on 2017/9/4.
 */

public class DaggerPresenter {
    DaggerView mDaggerView;

    @Inject
    public DaggerPresenter(DaggerView daggerView){
        mDaggerView=daggerView;
    }

    public void showText(){
        mDaggerView.showText(DaggerModel.getText());
    }
}
