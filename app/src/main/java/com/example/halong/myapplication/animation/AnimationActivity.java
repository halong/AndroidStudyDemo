package com.example.halong.myapplication.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.halong.myapplication.R;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnAlph;
    private Button mBtnScale;
    private Button mBtnTranslate;
    private Button mBtnRotate;
    private ImageView mImageView;
    /**
     * RotateAnimation
     */
    private Button mBtnFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
    }

    private void initView() {
        mBtnAlph = (Button) findViewById(R.id.btn_alph);
        mBtnAlph.setOnClickListener(this);
        mBtnScale = (Button) findViewById(R.id.btn_scale);
        mBtnScale.setOnClickListener(this);
        mBtnTranslate = (Button) findViewById(R.id.btn_translate);
        mBtnTranslate.setOnClickListener(this);
        mBtnRotate = (Button) findViewById(R.id.btn_rotate);
        mBtnRotate.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.image_view);
        mBtnFrame = (Button) findViewById(R.id.btn_frame);
        mBtnFrame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alph:
                AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
                alphaAnimation.setDuration(2000);
                alphaAnimation.setFillAfter(true);
                alphaAnimation.setRepeatCount(3);
                mImageView.startAnimation(alphaAnimation);
                break;
            case R.id.btn_scale:
                ScaleAnimation scaleAnimation
                        = new ScaleAnimation(0f, 4f, 0f, 4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(2000);
                scaleAnimation.setFillAfter(true);
                mImageView.startAnimation(scaleAnimation);
                break;
            case R.id.btn_translate:
//                TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -3f, Animation.RELATIVE_TO_SELF, 3f, Animation.RELATIVE_TO_SELF, -3f, Animation.RELATIVE_TO_SELF, 3f);
//                translateAnimation.setDuration(3000);
//                translateAnimation.setFillAfter(true);

                Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
                mImageView.startAnimation(translateAnimation);
                break;
            case R.id.btn_rotate:
                RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f);
                rotateAnimation.setDuration(3000);
                rotateAnimation.setFillAfter(true);
                mImageView.startAnimation(rotateAnimation);
                break;
            case R.id.btn_frame:
//                mImageView.setImageResource(R.drawable.frame_anim);
//                AnimationDrawable animationDrawable=(AnimationDrawable)mImageView.getDrawable();
//                animationDrawable.start();

                AnimationDrawable animationDrawable=new AnimationDrawable();
                animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading1),200);
                animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading3),200);
                animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading6),200);
                animationDrawable.addFrame(getResources().getDrawable(R.drawable.loading8),200);
                animationDrawable.setOneShot(false);
                mImageView.setImageDrawable(animationDrawable);
                animationDrawable.start();
                break;
        }
    }
}
