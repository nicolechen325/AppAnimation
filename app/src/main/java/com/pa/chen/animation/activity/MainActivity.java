package com.pa.chen.animation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pa.chen.animation.R;
import com.pa.chen.animation.explosion.ExplosionActivity;
import com.pa.chen.animation.loading.LoadingActivity;

//动画主页面
public class MainActivity extends Activity {

    Button mBtnProperty;
    Button mBtnTween;
    Button mBtnScroller;
    Button mBtnExplosion;
    Button mBtnShowLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        mBtnScroller= (Button) findViewById(R.id.btn_scroller);
        mBtnProperty = (Button) findViewById(R.id.btn_anim_property);
        mBtnTween = (Button) findViewById(R.id.btn_anim_tween);
        mBtnExplosion = (Button) findViewById(R.id.btn_explosion);
        mBtnShowLoading = (Button) findViewById(R.id.btn_showloading);

        mBtnShowLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LoadingActivity.class));
                startActivity(new Intent(MainActivity.this, com.pa.chen.animation.container.MainActivity.class));
            }
        });
        mBtnProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnimPropertyActivity.class));
            }
        });
        mBtnTween.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnimTweenActivity.class));
            }
        });
        mBtnScroller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScrollerActivity.class));
            }
        });
        mBtnExplosion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExplosionActivity.class));
            }
        });
    }


}
