package com.example.halong.myapplication.ui.pulltorefresh;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by halong on 2017/9/25.
 */

public class MyRefreshHeader extends LinearLayout implements MyRefreshView.MyHeader {
    private int y1;

    public MyRefreshHeader(Context context) {
        super(context);
    }

    @Override
    public void onPress(int y) {
        y1=y;
    }

    @Override
    public void onMove(int y) {

    }

    @Override
    public void onRelease() {

    }
}
