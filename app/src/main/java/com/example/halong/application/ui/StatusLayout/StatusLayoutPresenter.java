package com.example.halong.application.ui.StatusLayout;

/**
 * Created by halong on 2018/1/18.
 */

public class StatusLayoutPresenter {
    private StatusLayoutActivity mActivity;

    public StatusLayoutPresenter(StatusLayoutActivity activity) {
        mActivity = activity;
    }

    public void showInitView(){
        mActivity.initView(StatusLayoutModel.getStrings(),StatusLayoutModel.getBitmaps(mActivity));
    }


    public void detach() {
        mActivity = null;
    }

}
