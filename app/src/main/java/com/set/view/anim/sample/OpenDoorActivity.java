package com.set.view.anim.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.set.view.R;
import com.set.view.widget.PullDoorView;

/**
 * 开门动画
 */
public class OpenDoorActivity extends Activity implements PullDoorView.PullDoorCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_open_door);
        PullDoorView pullDoorView = (PullDoorView) findViewById(R.id.pull_door_view);
        pullDoorView.setmCallback(this);
    }

    @Override
    public void onPullDoorSuccess() {
        finish();
    }
}
