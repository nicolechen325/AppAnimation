package com.set.ui.image;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.set.ui.R;

/**
 * chen
 */
public class ImageScaleActivity extends AppCompatActivity {
    boolean isVisiable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_scale);

        final ImageView mImageOriginal = findViewById(R.id.image_original);
        final TextView mTvOriginal = findViewById(R.id.original);
        mTvOriginal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisiable) {
                    mImageOriginal.setVisibility(View.GONE);
                    mTvOriginal.setText("显示原图");
                    isVisiable = false;
                } else {
                    mImageOriginal.setVisibility(View.VISIBLE);
                    mTvOriginal.setText("关上原图");
                    isVisiable = true;
                }
            }
        });
    }

}