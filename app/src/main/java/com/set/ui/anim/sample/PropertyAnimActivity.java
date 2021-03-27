package com.set.ui.anim.sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.set.ui.R;

/**
 * 属性动画
 */
public class PropertyAnimActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_2 = "PropertyAnimActivity";
    TextView mTvAnimWidget;
    TextView mTvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_property_sample);
        mTvAnimWidget = findViewById(R.id.id_ball);
        mTvLog = findViewById(R.id.id_log);

        TextView mBtnTranslate = findViewById(R.id.btn_translate);
        TextView mBtnRotate = findViewById(R.id.btn_rotate);
        TextView mBtnScale = findViewById(R.id.btn_scale);
        TextView mBtnAlpha = findViewById(R.id.btn_alpha);

        TextView mBtnValuesHolder = findViewById(R.id.btn_property_values_holder);
        TextView mBtnViewAnim = findViewById(R.id.btn_view_anim);

        mBtnTranslate.setOnClickListener(this);
        mBtnAlpha.setOnClickListener(this);
        mBtnRotate.setOnClickListener(this);
        mBtnScale.setOnClickListener(this);
        mBtnValuesHolder.setOnClickListener(this);
        mBtnViewAnim.setOnClickListener(this);
    }

    /**
     * Y轴旋转
     * <p>
     * 动画更新的时候不断调用setXxx更新属性,在setRotationX的时候如果没有重绘,
     * 在AnimatorUpdateListener里需增加view.invalidate();
     * rotationX属性就是通过setRotationX在一定duration时间内不断给属性赋值 从0到180
     *
     * @param view
     */
    public void rotateYAnim(final View view) {
        float rotation = view.getRotationY();
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotationY", rotation,
                rotation + 180F).setDuration(2000);
        anim.addUpdateListener(animation -> {
            float cVal = animation.getAnimatedFraction();//运行比例
            Log.d(TAG_2, cVal + "");
            logView(cVal);
        });
        anim.start();
    }

    /**
     * X轴平移
     * 在平移中，mLeft值固定，距离父视图左侧的距离
     * 改变的是translationX和x值
     * x = mLeft + getTranslationX();
     * translationX是偏移值。
     * 偏移0-Value,视图向右移Value的距离,
     * 即距离左边俯视图的距离变大Value。
     *
     * @param view
     */
    public void translationXAnim(final View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationX", 0,
                300).setDuration(2000);
        anim.addUpdateListener(animation -> {
            float mFra = animation.getAnimatedFraction();//运行比例
            logView(mFra);
        });
        anim.start();
    }

    /**
     * 透明度
     *
     * @param view
     */
    public void alphaAnim(final View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1, 0.3f, 1);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = animation.getAnimatedFraction();
                logView(cVal);
            }
        });
        animator.start();
    }

    /**
     * Scale
     *
     * @param view
     */
    public void scaleAnim(final View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleX", 1, 0.3f);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = animation.getAnimatedFraction();
                Log.d(TAG_2, cVal + "");
                logView(cVal);
            }
        });
        animator.start();
    }

    /**
     * 一个动画更改多个效果：使用 propertyValuesHolder 并不是多个动画的叠加
     * 一个 PropertyValuesHolder 代表一个个属性 在一定时间的值的范围
     *
     * @param view
     */
    public void propertyValuesHolder(View view) {
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("alpha", 1f,
                0.2f, 1f);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0.2f, 1f);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0.2f, 1f);
        PropertyValuesHolder pvh4 = PropertyValuesHolder.ofFloat("rotationY", 0,
                180F);
        PropertyValuesHolder pvh5 = PropertyValuesHolder.ofFloat("translationY", 0,
                300);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, pvh1, pvh2, pvh3, pvh4, pvh5);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = animation.getAnimatedFraction();
                Log.d(TAG_2, cVal + "");
                logView(cVal);
            }
        });
        objectAnimator.setDuration(5000).start();
    }

    /**
     * View 内部 mAnimator
     *
     * @param view
     */
    public void viewAnimate(final View view) {
        view.animate()
                .alpha(0.4f)//3秒内变透明度 0.4f
                .y(1200)//往y方向增加1200像素
                .x(500)//往x方向增加200像素
                .setDuration(5000)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        /*view动画结束后 执行*/
                        view.setAlpha(1.0f);
                    }
                }).start();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_translate:
                translationXAnim(mTvAnimWidget);
                break;
            case R.id.btn_rotate:
                rotateYAnim(mTvAnimWidget);
                break;
            case R.id.btn_alpha:
                alphaAnim(mTvAnimWidget);
                break;
            case R.id.btn_scale:
                scaleAnim(mTvAnimWidget);
                break;
            case R.id.btn_property_values_holder:
                propertyValuesHolder(mTvAnimWidget);
                break;
            case R.id.btn_view_anim:
                viewAnimate(mTvAnimWidget);
                break;
            default:
                break;
        }
    }

    private void logView(float mFra) {
        StringBuilder printLog = new StringBuilder();
        printLog.append("Fraction=");
        printLog.append(mFra);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
