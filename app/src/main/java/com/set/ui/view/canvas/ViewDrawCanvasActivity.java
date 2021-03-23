package com.set.ui.view.canvas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.set.ui.R;

/**
 * View 绘制
 */
public class ViewDrawCanvasActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_3 = "ViewDrawCanvasActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_view_draw_canvas);
        initView();
    }

    protected void initView() {
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
    }
}
