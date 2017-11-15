package com.example.halong.myapplication.SwipeRecyclerView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

/**
 * Created by halong on 2017/10/12.
 */

public class MyMenuItem extends HorizontalScrollView {

    private int width;
    private boolean once=true;

    public MyMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        setHorizontalScrollBarEnabled(false);

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        if(once){

        }


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return super.onTouchEvent(ev);
    }
}
