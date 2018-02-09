package com.example.halong.application.ui.RoundImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatImageView;

/**
 * Created by halong on 2018/1/22.
 */

public class RoundImageView extends AppCompatImageView {
    private Paint paint;
    public RoundImageView(Context context) {
        this(context, null);
    }
    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint=new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (null != drawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            int x=canvas.getWidth();
            int y=canvas.getHeight();
            int r=x<y?x/2:y/2;

            Bitmap b = getCircleBitmap(bitmap,bitmap.getWidth()/2,bitmap.getHeight()/2,r);

            canvas.drawBitmap(b,0f,0f,paint);
        } else {
            super.onDraw(canvas);
        }
    }


    /**
     * 裁剪Bitmap成圆形
     * @param bitmap  原始的Bitmap
     * @param x   相对于原始的Bitmap的圆心x坐标
     * @param y   相对于原始的Bitmap的圆心y坐标
     * @param r   圆形半径
     * @return   裁剪好的Bitmap
     */
    private Bitmap getCircleBitmap(Bitmap bitmap,int x,int y,int r) {
        Bitmap outputBitmap = Bitmap.createBitmap(2*r,2*r,Bitmap.Config.ARGB_8888); //确定Bitmap的大小
        Canvas canvas = new Canvas(outputBitmap);//Canvas绘制后转换成Bitmap

        Paint paint=new Paint();
        paint.setAntiAlias(true);//去除锯齿
        canvas.drawCircle(r, r, r, paint);//先画个圆

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));// 设置绘制模式  只绘制圆范围的图画
        canvas.drawBitmap(bitmap,r-x,r-y,paint); //移动原始的Bitmap使其中心对准圆心，默认左上角对准坐标原点

        return outputBitmap;
    }


}
