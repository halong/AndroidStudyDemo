package com.example.halong.myapplication.others.Dagger2.mvp;

import javax.inject.Inject;

/**
 * Created by halong on 2017/9/4.
 */

public class MyPresenter {
    MyView mMyView;
    MyModel mMyModel;

    @Inject
    public MyPresenter(MyView myView, MyModel myModel){
        mMyView = myView;
        mMyModel = myModel;
    }

    public void showText(){
        mMyView.showText(mMyModel.getText());
    }

    public void uploadText(String s){
        mMyModel.setText(s);
    }
}
