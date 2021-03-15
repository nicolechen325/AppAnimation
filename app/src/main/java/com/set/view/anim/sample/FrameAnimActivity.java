package com.set.view.anim.sample;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.set.view.R;

/**
 * 帧动画
 */
public class FrameAnimActivity extends Activity {

    private static final int ANIMATION_INTERVAL = 400;// 200ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        ImageView imageView = findViewById(R.id.imageview);


        AnimationDrawable anim = new AnimationDrawable();
        for (int i = 0; i <= 7; i++) {
            int id = getResources().getIdentifier("heart" + i, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            anim.addFrame(drawable, ANIMATION_INTERVAL);
        }
        anim.setOneShot(false);
        imageView.setImageDrawable(anim);
        anim.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
