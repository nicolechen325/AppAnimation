package com.set.view.anim.sample;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.set.view.R;

/**
 * 帧动画
 */
public class FrameAnimActivity extends AppCompatActivity {
    private static final int ANIMATION_INTERVAL = 400;// 200ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_frame_sample);
        ImageView imageView = findViewById(R.id.img_frame);

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

}
