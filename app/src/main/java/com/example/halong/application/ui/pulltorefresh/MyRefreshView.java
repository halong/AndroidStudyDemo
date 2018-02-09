package com.example.halong.application.ui.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by halong on 2017/9/25.
 */

public class MyRefreshView extends ListView {
    private MyHeader myHeader;

    public MyRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        addHeaderView();
    }

    public void setMyHeader(MyHeader header) {
        myHeader = header;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int y = (int) ev.getRawY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                myHeader.onPress(y);
                break;
            case MotionEvent.ACTION_MOVE:
                myHeader.onMove(y);
                break;
            case MotionEvent.ACTION_UP:
                myHeader.onRelease();
                break;
        }

        return super.onTouchEvent(ev);
    }

    public interface MyHeader {
        void onPress(int y);

        void onMove(int y);

        void onRelease();
    }
}
