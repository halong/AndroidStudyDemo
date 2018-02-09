package com.example.halong.application.ui.TempControlView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.halong.application.R;
import com.orhanobut.logger.Logger;

/**
 * 运行次序：onMeasure->onMeasure->onSizeChanged->onLayout->onMeasure->onMeasure->onLayout->onDraw
 * 在构造方法init初始化对象  与控件宽高无关的变量 eg：paint
 * 在onSizeChanged初始化与控件宽高有关的变量 eg： the center of circle  当SizeChange会自动invalidate();
 * 在onTouchEvent改变变量 invalidate();
 *
 * ##invalidate();会调用onDraw重绘 所以onDraw不要new对象
 * ##canvas.save()-->canvas.restore()保存和重置坐标系 canvas.translate() canvas.rotate() canvas.scale()改变画笔坐标
 * ##wrap_content问题
 * ##重置宽高问题
 * ##改变Layout问题
 */
public class TempControlView extends View {
    private Paint paint;//画笔
    private int degree;//指针角度相对于初始坐标系
    //圆心坐标
    private float centerX;
    private float centerY;

    private Bitmap bitmap;

    //构造方法三连发 **1,2是this  3是super
    public TempControlView(Context context) {
        this(context, null);
    }

    public TempControlView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TempControlView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
//        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(30);
//        paint.setTypeface(Typeface.SERIF);//serif字体类型


        bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Logger.d("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Logger.d("onLayout");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Logger.d("onSizeChanged");

        centerX = w / 2;//在onSizeChanged初始化宽高相关变量
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logger.d("onDraw");

        drawScale(canvas);
        drawHand(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                degree = getDegree(event.getX()-centerX, event.getY()-centerY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                degree = getDegree(event.getX()-centerX, event.getY()-centerY);
                invalidate();
                break;
        }
        return true;
    }

    //绘制刻度
    private void drawScale(Canvas canvas) {
        canvas.save();//保存当前状态

        //理解成移动的是坐标轴好了  可视范围不变  -->translate -- rotate --scale
        //先调整画笔坐标轴
        canvas.translate(centerX, centerY);//圆心坐标
        canvas.rotate(-220);

        for (int i = 0; i < 27; i++) {
            canvas.drawLine(centerY - 30, 0, centerY - 4, 0, paint);
            canvas.drawText(i+"",centerY,0,paint);
            canvas.rotate(10);
        }

        canvas.rotate(-50);//回到初始坐标
//       canvas.drawPoint(0,10,paint);//画点
//        canvas.drawLine(0, 0, getHeight() / 2 - 40, 0, paint);//画线
//       canvas.drawRect(-80, -80, 80, 80, paint);//画矩形
//       canvas.drawCircle(0,0,130,paint);//画圆

//        Path path=new Path();
//        path.moveTo(100,100);
//        path.lineTo(120,330);
//        path.lineTo(300,200);
//        path.close();
//        canvas.drawPath(path,paint);

        RectF rect = new RectF(-centerY + 46, -centerY + 46, centerY - 46, centerY - 46);
//        canvas.drawOval(rect,paint);//画椭圆
        canvas.drawArc(rect, -230, 280, false, paint);//画弧线
//        canvas.drawBitmap(bitmap,0,0,paint); //画图片

        canvas.restore();//回复到之前保存的状态
    }

    private void drawHand(Canvas canvas) {
        canvas.save();//保存当前状态

        canvas.translate(centerX, centerY);//先平移 再旋转
        canvas.rotate(degree);
        canvas.drawLine(0, 0, centerY - 40, 0, paint);
        canvas.drawText("我是中文",centerY-40,0,paint);
        canvas.drawBitmap(bitmap,30,0,paint);
        canvas.restore();//回复到之前保存的状态
    }

    private int getDegree(float x, float y) {
        int r = 0;
        if (x == 0) { //若x轴为0,
            if (y >= 0) {
                return 90;
            } else {
                return -90;
            }
        } else if (x > 0) { //在1,4象限
            r = (int) (Math.atan(y / x) / Math.PI * 180 + 0.5);
        } else if (x < 0) { //在2,3象限
            r = (int) (Math.atan(y / x) / Math.PI * 180 + 0.5);
            r += 180;
        }
        return r;
    }


    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    public int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

}
