package com.set.view.anim.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.set.view.R;

/**
 * View 属性
 */
public class ViewTranslationActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_3 = "ViewTranslationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_translation_view);
        initView();
    }

    protected void initView() {
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