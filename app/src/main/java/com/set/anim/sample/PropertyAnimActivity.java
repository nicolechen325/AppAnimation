package com.set.anim.sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.set.animation.R;

/**
 * 属性动画
 */
public class PropertyAnimActivity extends Activity implements View.OnClickListener {
    public static final String TAG_2 = "PropertyAnimActivity";

    TextView mTvAnimWidget;
    TextView mTvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pro_anim_sample);
        mTvAnimWidget = findViewById(R.id.id_ball);
        mTvLog = findViewById(R.id.id_log);

        TextView mBtnRotate = findViewById(R.id.btn_rotate);
        TextView mBtnTranslate = findViewById(R.id.btn_translate);
        TextView mBtnAlpha = findViewById(R.id.btn_alpha);
        TextView mBtnScale = findViewById(R.id.btn_scale);
        TextView mBtnValuesHolder = findViewById(R.id.btn_property_values_holder);
        TextView mBtnPWX = findViewById(R.id.btn_paowuxian);
        TextView mBtnViewAnim = findViewById(R.id.btn_view_anim);

        mBtnTranslate.setOnClickListener(this);
        mBtnAlpha.setOnClickListener(this);
        mBtnRotate.setOnClickListener(this);
        mBtnScale.setOnClickListener(this);
        mBtnValuesHolder.setOnClickListener(this);
        mBtnPWX.setOnClickListener(this);
        mBtnViewAnim.setOnClickListener(this);

    }

    /**
     * Y轴旋转
     *
     * @param view
     */
    public void rotateYAnim(final View view) {
        /*
         * 动画更新的时候不断调用setXxx更新属性,在setRotationX的时候如果没有重绘,
         * 在AnimatorUpdateListener里需增加view.invalidate();
         * rotationX属性就是通过setRotationX在一定duration时间内不断给属性赋值 从0到180 */
        float rotation = view.getRotationY();
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotationY", rotation,
                rotation + 180F).setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = animation.getAnimatedFraction();
                Log.d(TAG_2, cVal + "");
            }
        });
        anim.start();
    }

    /**
     * X轴平移
     *
     * @param view
     */
    public void translationXAnim(final View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationX", 0,
                300).setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            /*
            在平移中，mLeft值固定，距离父视图左侧的距离
            * 改变的是translationX和x值
            * x = mLeft + getTranslationX();
            * translationX是偏移值。
            * 偏移0-300,视图向右移300的距离,
            * 即距离左边俯视图的距离变大300。
            * */
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mFra = animation.getAnimatedFraction();//运行比例
                StringBuilder printLog = new StringBuilder();
                printLog.append("Fraction=");
                printLog.append(mFra);
                printLog.append("\n");
                printLog.append("mLeft=");
                printLog.append(mTvAnimWidget.getLeft());
                printLog.append("\n");
                printLog.append("translationX=");
                printLog.append(mTvAnimWidget.getTranslationX());
                printLog.append("\n");
                printLog.append("X=");
                printLog.append(mTvAnimWidget.getX());
                mTvLog.setText(printLog);
            }
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
                Log.d(TAG_2, cVal + "");
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

                StringBuilder printLog = new StringBuilder();
                printLog.append("Fraction=");
                printLog.append(cVal);
                printLog.append("\n");
                printLog.append("width=");
                printLog.append(mTvAnimWidget.getHeight());
                printLog.append("\n");
                printLog.append("translationX=");
                printLog.append(mTvAnimWidget.getTranslationX());
                printLog.append("\n");
                printLog.append("X=");
                printLog.append(mTvAnimWidget.getX());
                printLog.append("\n");
                printLog.append("mLeft=");
                printLog.append(mTvAnimWidget.getLeft());
                mTvLog.setText(printLog);
            }
        });
        animator.start();
    }

    /**
     * 一个动画更改多个效果：使用propertyValuesHolder 注意,并不是多个动画的叠加
     * 一个PropertyValuesHolder代表一个个属性 在一定时间的值的范围
     *
     * @param view
     */
    public void propertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0.5f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0.5f, 0.0f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0.5f, 0.0f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ)
                .setDuration(2000).start();
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
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        animator.start();
    }

    private class PaoWuLineEvaluator implements TypeEvaluator<PointF> {
        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            //frac是0-1；
            fraction *= 3;//按照3秒运动时间计算
            Log.d(TAG_2, fraction + "");
            // x方向100px/s ，则y方向0.5 * 200 * t * t，水平初速度100，重力加速度200计算
            PointF resultPointF = new PointF();
            resultPointF.x = 100 * fraction;
            resultPointF.y = 0.5f * 200 * (fraction) * (fraction);
            return resultPointF;
        }
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
                .setDuration(1000)
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
            case R.id.btn_paowuxian:
                paoWuXianAnim(mTvAnimWidget);
                break;
            case R.id.btn_view_anim:
                viewAnimate(mTvAnimWidget);
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
