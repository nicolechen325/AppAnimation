package com.pa.chen.animation.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.nineoldandroids.animation.ObjectAnimator;

//波浪效果动画
public class WavesView extends View {
    private static final String TAG = "WavesView";
    private static final float STRECH_FACTOR_A = 20;
    private static int OFFSET_Y = 0;//y轴位移
    private float[] mYPositions;
    private Paint mPaint;
    private float[] mDestYPositions;
    private int xOffSet;
    int waveMaxHeight;//波浪最大高度
    int drawTimes = 0;
    ObjectAnimator anim;

    public WavesView(Context context) {
        super(context);
        init(context);
    }

    public WavesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WavesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#9966B2FF"));
        Animation waveAn = new Animation() {
            float lastTiem;

            //该动画的改变不利用Matrix，而是利用动画的时间频率去改变View内数组
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                //  CLogUtils.d(LogTag,"applyTransformation:"+waveCurWeight);///虽然动画视图一直运行，但是是父视图重绘,需要在本onDraw里invalidate才可以
                //满足了5秒钟到顶
                if (interpolatedTime != lastTiem) {
                    for (int i = 0; i < getWidth(); i++) {
                        mYPositions[i] += waveMaxHeight * (interpolatedTime - lastTiem);//整体振幅上移,注意是每一个mYPositions自增，而不能增加后再让mYPositions等于它,//每次增加高度
                    }
                }
                lastTiem = interpolatedTime;
                super.applyTransformation(interpolatedTime, t);
            }
        };
//        if (AppConfig.skipSplashActvity) {
//            waveAn.setDuration(AppConfig.splashStartTime_debug);
//        } else {
//            waveAn.setDuration(AppConfig.splashStartTime);
//        }
        setAnimation(waveAn);
        waveAn.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mYPositions = new float[w];
        mDestYPositions = new float[w];

        float mCycleFactorW = (float) (2 * Math.PI / getWidth());
        for (int i = 0; i < getWidth(); i++) {
            mYPositions[i] = (float) (STRECH_FACTOR_A * Math.sin(mCycleFactorW * i) + OFFSET_Y);
        }
        waveMaxHeight = getHeight();
        //属性动画也可以实现
//        tween_anim_sample = ObjectAnimator.ofFloat(this, "a", 0,
//                getHeight()).setDuration(AppConfig.splashStartTime);
//        tween_anim_sample.start();
//        tween_anim_sample.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            float lastVal = 0;
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float cVal = animation.getAnimatedFraction();//满足了5秒钟到顶
//                waveCurWeight = waveMaxHeight * cVal;
//                if (cVal != lastVal) {
//                    for (int i = 0; i < getWidth(); i++) {
//                        mYPositions[i] += waveMaxHeight * (cVal - lastVal);//整体振幅上移,注意是每一个mYPositions自增，而不能增加后再让mYPositions等于它,//每次增加高度
//                    }
//                }
//                invalidate();
//                lastVal = cVal;
//            }
//        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawTimes++;//经过测试5秒钟的动画绘制了306次，平均60毫秒绘制一次，但不同的手机在5秒绘制的次数不同,性能低的手机只有100多次
        //CLogUtils.d(LogTag, "onDraw!!=" + drawTimes);
        if (xOffSet > 0) {
            System.arraycopy(mYPositions, xOffSet, mDestYPositions, 0, mYPositions.length - xOffSet);
            System.arraycopy(mYPositions, 0, mDestYPositions, mYPositions.length - xOffSet, xOffSet);
        }
        for (int i = 0; i < getWidth(); i++) {
            //mYPositions[i] +=getHeight()/300;
            canvas.drawLine(i, getHeight() - mDestYPositions[i], i, getHeight(), mPaint);
        }

        xOffSet += 28;//调节波动频率
        if (xOffSet >= getWidth()) {
            xOffSet = 0;
        }
        invalidate();
    }

}
