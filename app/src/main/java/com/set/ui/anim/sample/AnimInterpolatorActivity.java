package com.set.ui.anim.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

import com.set.ui.R;

/**
 * 插值器
 */
public class AnimInterpolatorActivity extends AppCompatActivity {
    public static final String TAG_1 = "AnimInterpolatorActivity";
    private View animView1;
    private View animView2;
    private View animView3;
    private View animView4;
    private View animView5;
    private View animView6;
    private View animView7;
    private View animView8;
    private View animView9;

    private Animation animation1;
    private Animation animation2;
    private Animation animation3;
    private Animation animation4;
    private Animation animation5;
    private Animation animation6;
    private Animation animation7;
    private Animation animation8;
    private Animation animation9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_interpolator_sample);
        initView();
        loadAnimation();
        startAnimation();
    }

    protected void initView() {
        animView1 = findViewById(R.id.img_tween_interpolator_1);
        animView2 = findViewById(R.id.img_tween_interpolator_2);
        animView3 = findViewById(R.id.img_tween_interpolator_3);
        animView4 = findViewById(R.id.img_tween_interpolator_4);
        animView5 = findViewById(R.id.img_tween_interpolator_5);
        animView6 = findViewById(R.id.img_tween_interpolator_6);
        animView7 = findViewById(R.id.img_tween_interpolator_7);
        animView8 = findViewById(R.id.img_tween_interpolator_8);
        animView9 = findViewById(R.id.img_tween_interpolator_9);
    }

    void loadAnimation() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;

        animation1 = new TranslateAnimation(0, widthPixels * 0.82f, 0, 0);
        animation1.setDuration(5000);
        animation2 = new TranslateAnimation(0, widthPixels * 0.82f, 0, 0);
        animation2.setDuration(5000);
        animation3 = new TranslateAnimation(0, widthPixels * 0.82f, 0, 0);
        animation3.setDuration(5000);
        animation4 = new TranslateAnimation(0, widthPixels * 0.82f, 0, 0);
        animation4.setDuration(5000);
        animation5 = new TranslateAnimation(0, widthPixels * 0.82f, 0, 0);
        animation5.setDuration(5000);
        animation6 = new TranslateAnimation(0, widthPixels * 0.82f, 0, 0);
        animation6.setDuration(5000);
        animation7 = new TranslateAnimation(0, widthPixels * 0.82f, 0, 0);
        animation7.setDuration(5000);
        animation8 = new TranslateAnimation(0, widthPixels * 0.82f, 0, 0);
        animation8.setDuration(5000);
        animation9 = new TranslateAnimation(0, widthPixels * 0.82f, 0, 0);
        animation9.setDuration(5000);

        //动画结束后保留结束状态
        animation1.setFillAfter(true);
        animation2.setFillAfter(true);
        animation3.setFillAfter(true);
        animation4.setFillAfter(true);
        animation5.setFillAfter(true);
        animation6.setFillAfter(true);
        animation7.setFillAfter(true);
        animation8.setFillAfter(true);
        animation9.setFillAfter(true);

        /**
         *   AccelerateDecelerateInterpolator：开始和结束的时候慢，中间快
         *   AccelerateInterpolator：开始的时候慢，然后加速
         *   AnticipateInterpolator：开始先后退，然后向前
         *   AnticipateOvershootInterpolator： 开始先后退，然向前到超标，最后回到最终值
         *   BounceInterpolator ：最后会反弹
         *   CycleInterpolator：动画会重复一定的周期数
         *   DecelerateInterpolator：开始快，然后减速
         *   LinearInterpolator：变化匀速
         *   OvershootInterpolator：到达最终值后超标，再回到最终值
         *   TimeInterpolator：用来自定义插值器
         */
        animation1.setInterpolator(new AccelerateDecelerateInterpolator());
        animation2.setInterpolator(new AccelerateInterpolator());
        animation3.setInterpolator(new AnticipateInterpolator());
        animation4.setInterpolator(new AnticipateOvershootInterpolator());
        animation5.setInterpolator(new BounceInterpolator());
        animation6.setInterpolator(new CycleInterpolator(2.0f));
        animation7.setInterpolator(new DecelerateInterpolator());
        animation8.setInterpolator(new LinearInterpolator());
        animation9.setInterpolator(new OvershootInterpolator());

    }


    void startAnimation() {
        animView1.setAnimation(animation1);
        animView2.setAnimation(animation2);
        animView3.setAnimation(animation3);
        animView4.setAnimation(animation4);
        animView5.setAnimation(animation5);
        animView6.setAnimation(animation6);
        animView7.setAnimation(animation7);
        animView8.setAnimation(animation8);
        animView9.setAnimation(animation9);
    }

}
