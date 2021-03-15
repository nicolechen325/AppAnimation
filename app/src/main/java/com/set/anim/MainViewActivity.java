package com.set.anim;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.set.anim.dialog3d.Dialog3dAnim;

import com.set.anim.sample.FasterImageAnimActivity;
import com.set.anim.sample.FrameAnimActivity;
import com.set.anim.sample.OpenDoorActivity;
import com.set.anim.sample.ScrollerActivity;
import com.set.anim.explosion.ExplosionActivity;
import com.set.anim.loading.LoadingActivity;
import com.set.anim.sample.PropertyAnimActivity;
import com.set.anim.sample.TweenAnimActivity;
import com.set.anim.sample.TweenInterpolatorActivity;
import com.set.anim.sample.ViewTranslationActivity;
import com.set.anim.widget.ImageProgressDialog;
import com.set.animation.R;

/**
 * 主页面
 */
public class MainViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }

    public void btnPropertyAnim(View view) {
        startActivity(new Intent(MainViewActivity.this, PropertyAnimActivity.class));
    }

    public void btnTweenAnim(View view) {
        startActivity(new Intent(MainViewActivity.this, TweenAnimActivity.class));
    }

    //插值器
    public void btnTweenInterpolatorAnim(View view) {
        startActivity(new Intent(MainViewActivity.this, TweenInterpolatorActivity.class));
    }

    //帧动画
    public void btnFrameAnim(View view) {
        startActivity(new Intent(MainViewActivity.this, FrameAnimActivity.class));
    }

    //ImageView快速闪动
    public void btnFasterImageAnim(View view) {
        startActivity(new Intent(MainViewActivity.this, FasterImageAnimActivity.class));
    }

    public void btnScroller(View view) {
        startActivity(new Intent(MainViewActivity.this, ScrollerActivity.class));
    }

    public void btnOpenDoor(View view) {
        startActivity(new Intent(MainViewActivity.this, OpenDoorActivity.class));
    }

    public void btnExplosion(View view) {
        startActivity(new Intent(MainViewActivity.this, ExplosionActivity.class));
    }

    public void btnShowLoading(View view) {
        startActivity(new Intent(MainViewActivity.this, LoadingActivity.class));
    }

    //视图属性
    public void btnTranslationView(View view) {
        startActivity(new Intent(MainViewActivity.this, ViewTranslationActivity.class));
    }

    public void btn3dDialog(View view) {
        final Dialog3dAnim dialog = new Dialog3dAnim(this);
        dialog.show();

        final ImageProgressDialog prDialog = new ImageProgressDialog(MainViewActivity.this,
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
    }

}
