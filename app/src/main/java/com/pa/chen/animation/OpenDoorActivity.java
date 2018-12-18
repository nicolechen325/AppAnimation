package com.pa.chen.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import com.pa.chen.animation.container.ContainerActivity;
import com.pa.chen.animation.explosion.ExplosionActivity;
import com.pa.chen.animation.loading.LoadingActivity;
import com.pa.chen.animation.sample.PropertyAnimActivity;
import com.pa.chen.animation.sample.ScrollerActivity;
import com.pa.chen.animation.sample.TweenAnimActivity;
import com.pa.chen.animation.widget.PullDoorView;

//开门动画
public class OpenDoorActivity extends Activity implements PullDoorView.PullDoorCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_open_door);
        PullDoorView pullDoorView =(PullDoorView)findViewById(R.id.pull_door_view);
        pullDoorView.setmCallback(this);
    }

    @Override
    public void onPullDoorSuccess() {
        startActivity(new Intent(OpenDoorActivity.this, DispatcherActivity.class));
        finish();
    }
}
