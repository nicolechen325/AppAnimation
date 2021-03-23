package com.set.ui.anim.dialog3d;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.set.ui.R;

/**
 * 3D动画
 */
public class Anim3dActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_1 = "Anim3dActivity";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_3d_sample);
        initView();
    }

    protected void initView() {
        mTextView = findViewById(R.id.tv_3d_dialog);
        mTextView.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_3d_dialog:
                final Dialog3dAnim dialog = new Dialog3dAnim(this);
                dialog.show();
                break;
            default:
                break;
        }
    }
}
