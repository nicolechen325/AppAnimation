package com.set.ui.anim.opendoor;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;

import com.set.ui.R;

/**
 * 开门动画
 */
public class OpenDoorActivity extends AppCompatActivity implements PullDoorView.PullDoorCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_open_door);
        PullDoorView pullDoorView = findViewById(R.id.pull_door_view);
        pullDoorView.setmCallback(this);
    }

    @Override
    public void onPullDoorSuccess() {
        finish();
    }
}
