package com.pa.chen.animation.activity;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.pa.chen.animation.R;

/*
补间动画分析：只需要指定动画开始 动画结束关键帧 以及持续时间、动画变化的中间帧 由系统计算
Interpolator接口控制动画变化的速度 包括匀速 加速减速 抛物线等
 */
public class AnimTweenActivity extends Activity implements View.OnClickListener {
    Button mBtnAnim;
    ImageView mImageView;
    Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_tween);
        initView();
    }

    protected void initView() {
        mImageView= (ImageView) findViewById(R.id.iv_animation_obj);
        mBtnAnim = (Button) findViewById(R.id.btn_anim_begin);
        mBtnAnim.setOnClickListener(this);
        mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
        mAnimation.setFillAfter(true);//动画结束后保留结束状态
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG.TAG_1, "--onAnimationStart");
                Log.d(TAG.TAG_1, mImageView.getX() + "x" + mImageView.getTranslationX());
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG.TAG_1, "--onAnimationEnd");
                Log.d(TAG.TAG_1, mImageView.getMeasuredHeight() + "x" + mImageView.getHeight());
                Log.d(TAG.TAG_1, mImageView.getX() + "x" + mImageView.getTranslationX());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG.TAG_1, "--onAnimationRepeat");
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_anim_begin:
                mImageView.startAnimation(mAnimation);//Android动画,在父视图的范围内做动画
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
