package com.pa.chen.animation.container;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.pa.chen.animation.R;

public class ContainerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
    }

}

