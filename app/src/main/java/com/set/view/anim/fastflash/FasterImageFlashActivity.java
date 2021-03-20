package com.set.view.anim.fastflash;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.set.view.R;

/**
 * 帧动画
 */
public class FasterImageFlashActivity extends AppCompatActivity {

    FasterAnimContainer mFasterAnimationsContainer;
    private static final int[] IMAGE_RESOURCES = {R.drawable.anim_0, R.drawable.anim_1,
            R.drawable.anim_2, R.drawable.anim_3, R.drawable.anim_4,
            R.drawable.anim_5, R.drawable.anim_6, R.drawable.anim_7
    };

    private static final int ANIMATION_INTERVAL = 400;// 200ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_faster_flash);
        ImageView imageView = findViewById(R.id.img_faster_flash);
        mFasterAnimationsContainer = FasterAnimContainer
                .getInstance(imageView);
        mFasterAnimationsContainer.addAllFrames(IMAGE_RESOURCES,
                ANIMATION_INTERVAL);
        mFasterAnimationsContainer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFasterAnimationsContainer.stop();
    }
}
