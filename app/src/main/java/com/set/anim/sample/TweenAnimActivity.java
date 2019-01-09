package com.set.anim.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.pa.chen.animation.R;
import com.set.anim.logger.LogTag;

//补间动画示例
public class TweenAnimActivity extends Activity {
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tween_anim_sample);
        initView();
    }

    protected void initView() {
        mTextView= (TextView) findViewById(R.id.tv_anim);

        final Animation mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_sample);
        mAnimation.setFillAfter(true);//动画结束后保留结束状态
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(LogTag.TAG_1, "onAnimationStart");
                Log.d(LogTag.TAG_1, mTextView.getMeasuredHeight() + "x" + mTextView.getHeight());
                Log.d(LogTag.TAG_1, mTextView.getX() + "x" + mTextView.getTranslationX());
                //动画中X和translationX都未改变，都是0。
                //宽高都未改变
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(LogTag.TAG_1, "onAnimationEnd");
                Log.d(LogTag.TAG_1, mTextView.getMeasuredHeight() + "x" + mTextView.getHeight());
                Log.d(LogTag.TAG_1, mTextView.getX() + "x" + mTextView.getTranslationX());
                //动画中，虽然缩小了，但是X和translationX都未改变，都是0。
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Android动画,在父视图的范围内做动画
                mTextView.startAnimation(mAnimation);
            }
        },500);
    }

}
