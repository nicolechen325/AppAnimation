package com.set.ui.anim.sample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.set.ui.R;
import com.set.ui.anim.xanim.AnimationImageView;
import com.set.ui.anim.xanim.MAnimationDrawable;

/**
 * 帧动画
 */
public class FrameAnimActivity extends AppCompatActivity {
    private static final int ANIMATION_INTERVAL = 400;// 200ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_frame_sample);
        AnimationImageView imageView = findViewById(R.id.img_frame);

        MAnimationDrawable anim = new MAnimationDrawable();
        for (int i = 0; i <= 7; i++) {
            int id = getResources().getIdentifier("heart" + i, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            anim.addFrame(drawable, ANIMATION_INTERVAL);
        }
        anim.setOneShot(false);
        imageView.setImageDrawable(anim);//这一步会 d.setCallback(this);，设置callback是 imageview。
        anim.start();
    }

}
