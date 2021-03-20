package com.set.view.anim.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.set.view.widget.DrawAnimationTextView;
import com.set.view.R;

/**
 * 补间动画
 */
public class TweenAnimActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_1 = "TweenAnimActivity";

    DrawAnimationTextView mTextViewAlpha;
    DrawAnimationTextView mTextViewTranslate;
    DrawAnimationTextView mTextViewRotate;
    DrawAnimationTextView mTextViewScale;
    DrawAnimationTextView mTextViewAll;

    Animation animationScale;
    Animation animationRotate;
    Animation animationTranslate;
    Animation animationAlpha;
    Animation animationAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_tween_sample);
        initView();
        loadAnimation();
    }

    protected void initView() {
        mTextViewAll = findViewById(R.id.tv_anim_all);
        mTextViewTranslate = findViewById(R.id.tv_anim_translate);
        mTextViewScale = findViewById(R.id.tv_anim_scale);
        mTextViewAlpha = findViewById(R.id.tv_anim_alpha);
        mTextViewRotate = findViewById(R.id.tv_anim_rotate);

        mTextViewAll.setOnClickListener(this);
        mTextViewTranslate.setOnClickListener(this);
        mTextViewAlpha.setOnClickListener(this);
        mTextViewScale.setOnClickListener(this);
        mTextViewRotate.setOnClickListener(this);
    }

    void loadAnimation() {
        animationAll = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_sample);
        animationAll.setFillAfter(true);//动画结束后保留结束状态
        animationAll.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG_1, "onAnimationStart");
                Log.d(TAG_1, mTextViewAll.getMeasuredHeight() + "x" + mTextViewAll.getHeight());
                Log.d(TAG_1, mTextViewAll.getX() + "x" + mTextViewAll.getTranslationX());
                //动画中X和translationX都未改变，都是0。
                //宽高都未改变
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG_1, "onAnimationEnd");
                Log.d(TAG_1, mTextViewAll.getMeasuredHeight() + "x" + mTextViewAll.getHeight());
                Log.d(TAG_1, mTextViewAll.getX() + "x" + mTextViewAll.getTranslationX());
                //动画中，虽然缩小了，但是X和translationX都未改变，都是0。
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        animationScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_scale);
        animationAlpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_alpha);
        animationTranslate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_translate);
        animationRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_rotate);

        animationScale.setFillAfter(true);//动画结束后保留结束状态
        animationAlpha.setFillAfter(true);
        animationTranslate.setFillAfter(true);
        animationRotate.setFillAfter(true);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_anim_all:
                mTextViewAll.startAnimation(animationAll);
                break;
            case R.id.tv_anim_translate:
                mTextViewTranslate.startAnimation(animationTranslate);
                break;
            case R.id.tv_anim_rotate:
                mTextViewRotate.startAnimation(animationRotate);
                break;
            case R.id.tv_anim_alpha:
                mTextViewAlpha.startAnimation(animationAlpha);
                break;
            case R.id.tv_anim_scale:
                mTextViewScale.startAnimation(animationScale);
                break;
        }
    }
}
