package com.pa.chen.animation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.pa.chen.animation.container.ContainerActivity;
import com.pa.chen.animation.dialog3d.Dialog3dAnim;
import com.pa.chen.animation.sample.ScrollerActivity;
import com.pa.chen.animation.explosion.ExplosionActivity;
import com.pa.chen.animation.loading.LoadingActivity;
import com.pa.chen.animation.sample.PropertyAnimActivity;
import com.pa.chen.animation.sample.TweenAnimActivity;
import com.pa.chen.animation.widget.ImageProgressDialog;

//动画主页面
public class DispatcherActivity extends Activity {
    FrameLayout frameLayout;
    Button mBtnProperty;
    Button mBtnTween;
    Button mBtnScroller;

    Button mBtnExplosion;
    Button mBtnShowLoading;
    Button mBtnContainer;
    Button mBtn3dDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        final ImageProgressDialog prDialog = new ImageProgressDialog(DispatcherActivity.this,
                R.style.dialog);
        prDialog.setCancelable(false);
        prDialog.show();
        prDialog.setContentText("正在加载,请稍后..");//在show之后，否则会空指针.
        prDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                prDialog.stopRotate();
            }
        });

        mBtnProperty = (Button) findViewById(R.id.btn_anim_property);
        mBtnTween = (Button) findViewById(R.id.btn_anim_tween);
        mBtnScroller = (Button) findViewById(R.id.btn_scroller);
        mBtnExplosion = (Button) findViewById(R.id.btn_explosion);
        mBtnShowLoading = (Button) findViewById(R.id.btn_showloading);
        mBtnContainer = (Button) findViewById(R.id.btn_container);
        mBtn3dDialog = (Button) findViewById(R.id.btn_3d_dialog);
    }

    public void btnPropertyAnim(View view) {
        startActivity(new Intent(DispatcherActivity.this, PropertyAnimActivity.class));
    }

    public void btnTweenAnim(View view) {
        startActivity(new Intent(DispatcherActivity.this, TweenAnimActivity.class));
    }

    public void btnScroller(View view) {
        startActivity(new Intent(DispatcherActivity.this, ScrollerActivity.class));
    }

    public void btnExplosion(View view) {
        startActivity(new Intent(DispatcherActivity.this, ExplosionActivity.class));
    }

    public void btnShowLoading(View view) {
        startActivity(new Intent(DispatcherActivity.this, LoadingActivity.class));
    }

    public void btnContainer(View view) {
        startActivity(new Intent(DispatcherActivity.this, ContainerActivity.class));
    }

    public void btn3dDialog(View view) {
        final Dialog3dAnim dialog = new Dialog3dAnim(this);
        dialog.show();
    }

}
