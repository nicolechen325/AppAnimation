package com.set.ui.anim.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.set.ui.anim.xanim.DrawAnimationTextView;
import com.set.ui.R;
import com.set.ui.anim.xanim.MAlphaAnimation;
import com.set.ui.anim.xanim.MRotateAnimation;
import com.set.ui.anim.xanim.MScaleAnimation;
import com.set.ui.anim.xanim.MTranslateAnimation;
import com.set.ui.anim.xanim.TransforiLog;

import java.text.DecimalFormat;

/**
 * 补间动画
 */
public class TweenAnimActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_1 = "TweenAnimActivity";
    private TextView mTextLog;

    DrawAnimationTextView mViewAlpha;
    DrawAnimationTextView mViewTranslate;
    DrawAnimationTextView mViewRotate;
    DrawAnimationTextView mViewScale;
    DrawAnimationTextView mViewAll;

    MScaleAnimation animationScale;
    MRotateAnimation animationRotate;
    MTranslateAnimation animationTranslate;
    MAlphaAnimation animationAlpha;
    Animation animationAll;

    private final int mDuration = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_tween_sample);
        initView();
        loadAnimation();
    }

    protected void initView() {
        mViewAll = findViewById(R.id.tv_anim_all);
        mViewTranslate = findViewById(R.id.tv_anim_translate);
        mViewScale = findViewById(R.id.tv_anim_scale);
        mViewAlpha = findViewById(R.id.tv_anim_alpha);
        mViewRotate = findViewById(R.id.tv_anim_rotate);

        mViewAll.setOnClickListener(this);
        mViewTranslate.setOnClickListener(this);
        mViewAlpha.setOnClickListener(this);
        mViewScale.setOnClickListener(this);
        mViewRotate.setOnClickListener(this);

        mTextLog = findViewById(R.id.anim_tv_view_log);

    }

    void loadAnimation() {
        animationAll = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_sample);
        animationAll.setFillAfter(true);//动画结束后保留结束状态
        animationAll.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG_1, "onAnimationStart");
                Log.d(TAG_1, mViewAll.getMeasuredHeight() + "x" + mViewAll.getHeight());
                Log.d(TAG_1, mViewAll.getX() + "x" + mViewAll.getTranslationX());
                //动画中X和translationX都未改变，都是0。
                //宽高都未改变
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG_1, "onAnimationEnd");
                Log.d(TAG_1, mViewAll.getMeasuredHeight() + "x" + mViewAll.getHeight());
                Log.d(TAG_1, mViewAll.getX() + "x" + mViewAll.getTranslationX());
                //动画中，虽然缩小了，但是X和translationX都未改变，都是0。
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

//        animationScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_scale);
//        animationAlpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_alpha);
//        animationTranslate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_translate);
//        animationRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_rotate);

        animationTranslate = new MTranslateAnimation(0, 150, 0, 0);
        animationTranslate.setFillAfter(true);//动画结束后保留结束状态
        animationTranslate.setDuration(mDuration);
        animationTranslate.setTransforiLog(new TransforiLogs(mViewTranslate));

        animationRotate = new MRotateAnimation(0.0f, 180.0f, 0.0f, 0.0f);
        animationRotate.setFillAfter(true);
        animationRotate.setDuration(mDuration);
        animationRotate.setTransforiLog(new TransforiLogs(mViewRotate));

        animationAlpha = new MAlphaAnimation(1, 0.3f);
        animationAlpha.setFillAfter(true);
        animationAlpha.setDuration(mDuration);
        animationAlpha.setTransforiLog(new TransforiLogs(mViewAlpha));

        animationScale = new MScaleAnimation(1f, 0.3f, 1, 0.3f);
        animationScale.setFillAfter(true);
        animationScale.setDuration(mDuration);
        animationScale.setTransforiLog(new TransforiLogs(mViewScale));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_anim_all:
                mViewAll.startAnimation(animationAll);
                break;
            case R.id.tv_anim_translate:
                mViewTranslate.startAnimation(animationTranslate);
                break;
            case R.id.tv_anim_rotate:
                mViewRotate.startAnimation(animationRotate);
                break;
            case R.id.tv_anim_alpha:
                mViewAlpha.startAnimation(animationAlpha);
                break;
            case R.id.tv_anim_scale:
                mViewScale.startAnimation(animationScale);
                break;
        }
    }

    public class TransforiLogs implements TransforiLog {

        private View animView;

        public TransforiLogs(View view) {
            this.animView = view;
        }

        @Override
        public void onTransLog(float progress) {
            DecimalFormat df = new DecimalFormat("0.00");
            String logmsg = "progress:" + df.format(progress) + "\n" +
                    "mLeft:" + animView.getLeft() + "\n" +
                    "mTop:" + animView.getTop() + "\n" +
                    "mRight:" + animView.getRight() + "\n" +
                    "mBottom:" + animView.getBottom() + "\n" +
                    "X:" + animView.getX() + "\n" +
                    "Y:" + animView.getY() + "\n" +
                    "translationX:" + animView.getTranslationX() + "\n" +
                    "translationY:" + animView.getTranslationY() + "\n" +
                    "mScrollX:" + animView.getScrollX() + "\n" +
                    "mScrollY:" + animView.getScrollY() + "\n" +
                    "rotationX:" + animView.getRotationX() + "\n" +
                    "rotationY:" + animView.getRotationY() + "\n" +
                    "scaleX:" + animView.getScaleX() + "\n" +
                    "scaleY:" + animView.getScaleY() + "\n" +
                    "alpha:" + animView.getAlpha();
            mTextLog.setText(logmsg);
            Log.d(TAG_1, "timeProgress:" + progress);
        }
    }
}
