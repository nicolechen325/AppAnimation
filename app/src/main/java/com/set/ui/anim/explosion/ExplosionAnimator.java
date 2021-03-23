package com.set.ui.anim.explosion;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

import java.util.Random;

/**
 * 爆炸效果,属性动画
 */
public class ExplosionAnimator extends ValueAnimator {
    static long DEFAULT_DURATION = 5000;
    private static final Interpolator DEFAULT_INTERPOLATOR = new AccelerateInterpolator(0.6f);
    private static final float END_VALUE = 1.4f;
    private static final float X = Utils.dp2Px(5);
    private static final float Y = Utils.dp2Px(20);
    private static final float V = Utils.dp2Px(2);
    private static final float W = Utils.dp2Px(1);
    private Paint mPaint;
    private Particle[] mParticles;//微粒碎片
    private Rect mBound;//爆炸区域
    private View mContainer;

    //爆炸图片Bitmap
    public ExplosionAnimator(View container, Bitmap bitmap, Rect bound) {
        mPaint = new Paint();
        mBound = new Rect(bound);
        int partLen = 15;//定义装255个碎片的数组
        mParticles = new Particle[partLen * partLen];
        Random random = new Random(System.currentTimeMillis());
        int w = bitmap.getWidth() / (partLen + 2);//宽高分17份
        int h = bitmap.getHeight() / (partLen + 2);
        //0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,分块越多，取的视图颜色值越全面
        for (int i = 0; i < partLen; i++) {
            for (int j = 0; j < partLen; j++) {
                mParticles[(i * partLen) + j] = generateParticle(bitmap.getPixel((j + 1) * w, (i + 1) * h), random);//根据分块，截取所有块的右下角的点的Bitmap颜色，
            }
        }
        mContainer = container;
        setFloatValues(0f, END_VALUE);//动画范围值
        setInterpolator(DEFAULT_INTERPOLATOR);//加速插值器
        setDuration(DEFAULT_DURATION);
    }

    //产生碎片
    private Particle generateParticle(int color, Random random) {
        Particle particle = new Particle();
        particle.color = color;// 颜色设置，每个碎片一种颜色
        particle.radius = V;
        if (random.nextFloat() < 0.2f) {//基础半径计算
            particle.baseRadius = V + ((X - V) * random.nextFloat());
        } else {
            particle.baseRadius = W + ((V - W) * random.nextFloat());
        }
        //nextFloat是通过随机数进行爆炸球x和y轴移动轨迹比例的控制
        float nextFloat = random.nextFloat();
        //Bound区域并不是视图的区域，是视图的一个扩大区域。
        //(0.2f到0.36f)height,20%
        //(0.2f到0.36f)height+(0f到0.72f)height，80%
        particle.top = mBound.height() * ((0.18f * random.nextFloat()) + 0.2f);
        particle.top = nextFloat < 0.2f ? particle.top : particle.top + ((particle.top * 0.2f) * random.nextFloat());
        //针对nextFloat<0.2时，(-0.9到0.9)height，20%
        //针对0.2<nextFloat<0.8时，(-0.45到0.45)height，60%
        //针对0.8<nextFloat<时，(-0.27到0.27)height，20%
        particle.bottom = (mBound.height() * (random.nextFloat() - 0.5f)) * 1.8f;
        float f = nextFloat < 0.2f ? particle.bottom : nextFloat < 0.8f ? particle.bottom * 0.6f : particle.bottom * 0.3f;
        particle.bottom = f;

        //计算mag，neg
        particle.mag = 4.0f * particle.top / particle.bottom;
        particle.neg = (-particle.mag) / particle.bottom;

        //计算爆炸球初始圆心坐标，不考虑透明时爆炸球开始出现的圆心点，所有球都集中在一小块区域中
        f = mBound.centerX() + (Y * (random.nextFloat() - 0.5f));//XY的中心点+随机值，不要都挤成一坨
        particle.baseCx = f;
        particle.cx = f;
        f = mBound.centerY() + (Y * (random.nextFloat() - 0.5f));
        particle.baseCy = f;
        particle.cy = f;

        //计算life，overflow
        particle.life = END_VALUE / 10 * random.nextFloat();//(0到0.14)
        particle.overflow = 0.4f * random.nextFloat();//(0到0.4f)
        particle.alpha = 1f;
        return particle;
    }

    public boolean draw(Canvas canvas) {
        if (!isStarted()) {
            return false;
        }
        //Particle particle = mParticles[25];//监控一个碎片小球的动向
        //Log.d("factor_anim", "frac:" + frac + "," + particle.cx + "," + particle.cy + "," + particle.radius);
        for (Particle particle : mParticles) {//为每一个碎片对象画一个圆
            float frac = (float) getAnimatedValue();
            particle.advance(frac);
            if (particle.alpha > 0f) {
                mPaint.setColor(particle.color);//每一个碎片都只有一种颜色。
                mPaint.setAlpha((int) (Color.alpha(particle.color) * particle.alpha));
                canvas.drawCircle(particle.cx, particle.cy, particle.radius, mPaint);
            }
        }
        mContainer.invalidate();
        return true;
    }

    @Override
    public void start() {
        super.start();
        mContainer.invalidate(mBound);
    }

    private class Particle {
        float alpha;//透明度比例,1不透明0透明
        int color;
        float cx;
        float cy;
        float radius;
        float baseCx;
        float baseCy;
        float baseRadius;
        float top;
        float bottom;
        float mag;
        float neg;
        float life;
        float overflow;

        //根据动画运行比例，修改内容包括，透明度，圆心位置，圆心半径
        public void advance(float factor) {
            float f = 0f;
            float normalization = factor / END_VALUE;//根据运行比例，normalization(0-1)
            //运行比例在(0到0.14)和(0.6到1)时，设置透明 ，每个爆炸球的life和overflow不同，使他们的出现与消失不同时的效果。
            if (normalization < life || normalization > 1f - overflow) {//life(0到0.14)与overflow(0到0.4f) ，
                alpha = 0f;//life是运行比例的十分之一x随机数,overflow是0.4x随机数，
                return;
            }
            //这个阶段不设置透明，取出首尾，再计算比例
            normalization = (normalization - life) / (1f - life - overflow);
            float f2 = normalization * END_VALUE;//(0-1.4)
            //在重计算的比例大于0.7时，后面的0.3比例开始让他逐渐变透明。使透明度有一个过度的阶段。
            if (normalization >= 0.7f) {
                f = (normalization - 0.7f) / 0.3f;
            }
            alpha = 1f - f;
            f = bottom * f2;//bottom不变，运动到底部位置x(0-1.4)，动画结束时，x轴移动最大位移将达1.4倍

            //基础baseXx不变，圆心点x坐标，
            //增加的权重值范围是(0-底部位置的1.4倍)
            cx = baseCx + f;
            cy = (float) (baseCy - this.neg * Math.pow(f, 2.0)) - f * mag;
            radius = V + (baseRadius - V) * f2;
        }
    }
}
