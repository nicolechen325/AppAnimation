package com.set.ui.anim.sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.set.ui.R;

/**
 * 估值器
 */
public class AnimEvaluatorActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_1 = "AnimEvaluatorActivity";
    TextView mTvAnimWidget;
    TextView mBtnAnim;
    TextView mTvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_evaluator_sample);
        initView();
        loadAnimation();
        startAnimation();
    }

    protected void initView() {
        mTvAnimWidget = findViewById(R.id.id_ball);
        mTvLog = findViewById(R.id.tv_log);
        mBtnAnim = findViewById(R.id.btn_paowuxian);
        mBtnAnim.setOnClickListener(this);
    }

    void loadAnimation() {
    }

    void startAnimation() {
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_paowuxian:
                paoWuXianAnim(mTvAnimWidget);
                break;
        }
    }

    /**
     * 抛物线ofInt,ofFloat由PointF代替
     *
     * @param target
     */
    public void paoWuXianAnim(final View target) {
        ValueAnimator animator = ValueAnimator.ofObject(new PaoWuLineEvaluator(), new PointF(0, 0));
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                target.setX(pointF.x);
                target.setY(pointF.y);
                target.setAlpha((float) 1.2 - animation.getAnimatedFraction());

                logView(pointF);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        animator.start();
    }

    /**
     * 估值器
     */
    private class PaoWuLineEvaluator implements TypeEvaluator<PointF> {
        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            //frac是0-1；
            fraction *= 3;//按照3秒运动时间计算
            Log.d(TAG_1, fraction + "");
            // x方向100px/s ，则y方向0.5 * 200 * t * t，水平初速度100，重力加速度200计算
            PointF resultPointF = new PointF();
            resultPointF.x = 100 * fraction;
            resultPointF.y = 0.5f * 200 * (fraction) * (fraction);
            return resultPointF;
        }
    }

    private void logView(PointF pointF) {
        StringBuilder printLog = new StringBuilder();
        printLog.append("pointF.x=" + pointF.x);
        printLog.append("\n");
        printLog.append("pointF.y=" + pointF.y);
        printLog.append("\n");
        printLog.append("mLeft=" + mTvAnimWidget.getLeft());
        printLog.append("\n");
        printLog.append("mTop=" + mTvAnimWidget.getTop());
        printLog.append("\n");
        printLog.append("mRight=" + mTvAnimWidget.getRight());
        printLog.append("\n");
        printLog.append("mBottom=" + mTvAnimWidget.getBottom());
        printLog.append("\n");
        printLog.append("mLeft=" + mTvAnimWidget.getLeft());
        printLog.append("\n");
        printLog.append("translationX=" + mTvAnimWidget.getTranslationX());
        printLog.append("\n");
        printLog.append("translationY=" + mTvAnimWidget.getTranslationY());
        printLog.append("\n");
        printLog.append("X=" + mTvAnimWidget.getX());
        printLog.append("\n");
        printLog.append("Y=" + mTvAnimWidget.getY());
        printLog.append("\n");
        printLog.append("alpha=" + mTvAnimWidget.getAlpha());
        printLog.append("\n");
        printLog.append("scaleX=" + mTvAnimWidget.getScaleX());
        printLog.append("\n");
        printLog.append("scaleY=" + mTvAnimWidget.getScaleY());
        printLog.append("\n");
        printLog.append("rotationX=" + mTvAnimWidget.getRotationX());
        printLog.append("\n");
        printLog.append("rotationY=" + mTvAnimWidget.getRotationY());
        printLog.append("\n");
        mTvLog.setText(printLog);
    }
}


