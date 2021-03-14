package com.set.anim.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.set.animation.R;

/**
 * 补间动画-插值器
 */
public class TweenInterpolatorActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_1 = "TweenInterpolatorrActivity";

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;


    Animation animation1;
    Animation animation2;
    Animation animation3;
    Animation animation4;
    Animation animation5;
    Animation animation6;
    Animation animation7;
    Animation animation8;
    Animation animation9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tween_interpolator_sample);
        initView();
        loadAnimation();
        startAnimation();
    }

    protected void initView() {
        imageView1 = findViewById(R.id.img_tween_interpolator_1);
        imageView2 = findViewById(R.id.img_tween_interpolator_2);
        imageView3 = findViewById(R.id.img_tween_interpolator_3);
        imageView4 = findViewById(R.id.img_tween_interpolator_4);
        imageView5 = findViewById(R.id.img_tween_interpolator_5);
        imageView6 = findViewById(R.id.img_tween_interpolator_6);
        imageView7 = findViewById(R.id.img_tween_interpolator_7);
        imageView8 = findViewById(R.id.img_tween_interpolator_8);
        imageView9 = findViewById(R.id.img_tween_interpolator_9);

    }

    void loadAnimation() {
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_interpolator);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_interpolator);
        animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_interpolator);
        animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_interpolator);
        animation5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_interpolator);
        animation6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_interpolator);
        animation7 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_interpolator);
        animation8 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_interpolator);
        animation9 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tween_anim_interpolator);

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

//        AccelerateDecelerateInterpolator：开始和结束的时候慢，中间快
//        AccelerateInterpolator：开始的时候慢，然后加速
//        AnticipateInterpolator：开始先后退，然后向前
//        AnticipateOvershootInterpolator： 开始先后退，然向前到超标，最后回到最终值
//        BounceInterpolator ：最后会反弹
//        CycleInterpolator：动画会重复一定的周期数
//        DecelerateInterpolator：开始快，然后减速
//        LinearInterpolator：变化匀速
//        OvershootInterpolator：到达最终值后超标，再回到最终值
//        TimeInterpolator：用来自定义插值器

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
        imageView1.setAnimation(animation1);
        imageView2.setAnimation(animation2);
        imageView3.setAnimation(animation3);
        imageView4.setAnimation(animation4);
        imageView5.setAnimation(animation5);
        imageView6.setAnimation(animation6);
        imageView7.setAnimation(animation7);
        imageView8.setAnimation(animation8);
        imageView9.setAnimation(animation9);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_tween_interpolator_1:
                break;

        }
    }
}
