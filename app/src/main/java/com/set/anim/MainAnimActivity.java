package com.set.anim;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.set.anim.dialog3d.Dialog3dAnim;

//import com.set.anim.jbox2dview.MobikeView;

import com.set.anim.sample.FasterImageAnimActivity;
import com.set.anim.sample.FrameAnimActivity;
import com.set.anim.sample.ScrollerActivity;
import com.set.anim.explosion.ExplosionActivity;
import com.set.anim.loading.LoadingActivity;
import com.set.anim.sample.PropertyAnimActivity;
import com.set.anim.sample.TweenAnimActivity;
import com.set.anim.widget.ImageProgressDialog;
import com.set.animation.R;

//摩擦系数，0-1之间
//能量损失率，0-1之间
//物体的密度
//世界与屏幕的比率

/**
 * 动画主页面
 */
public class MainAnimActivity extends Activity {
    private SensorManager sensorManager;
    private Sensor defaultSensor;

//    private MobikeView mobikeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        defaultSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

//        mobikeView = (MobikeView) findViewById(R.id.mobike_view);

    }

    public void btnPropertyAnim(View view) {
        startActivity(new Intent(MainAnimActivity.this, PropertyAnimActivity.class));
    }

    public void btnTweenAnim(View view) {
        startActivity(new Intent(MainAnimActivity.this, TweenAnimActivity.class));
    }

    public void btnFrameAnim(View view) {
        startActivity(new Intent(MainAnimActivity.this, FrameAnimActivity.class));
    }

    public void btnFasterImageAnim(View view) {
        startActivity(new Intent(MainAnimActivity.this, FasterImageAnimActivity.class));
    }


    public void btnScroller(View view) {
        startActivity(new Intent(MainAnimActivity.this, ScrollerActivity.class));
    }

    public void btnOpenDoor(View view) {
        startActivity(new Intent(MainAnimActivity.this, OpenDoorActivity.class));
    }

    public void btnExplosion(View view) {
        startActivity(new Intent(MainAnimActivity.this, ExplosionActivity.class));
    }

    public void btnShowLoading(View view) {
        startActivity(new Intent(MainAnimActivity.this, LoadingActivity.class));
    }

    public void btn3dDialog(View view) {
        final Dialog3dAnim dialog = new Dialog3dAnim(this);
        dialog.show();

        final ImageProgressDialog prDialog = new ImageProgressDialog(MainAnimActivity.this,
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

    @Override
    protected void onStart() {
        super.onStart();
//        mobikeView.getmMobike().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        mobikeView.getmMobike().onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listerner, defaultSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listerner);
    }

    private SensorEventListener listerner = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1] * 2.0f;
                Log.d("SensorEventListener", "x:" + x + ",y:" + y);
//                mobikeView.getmMobike().onSensorChanged(-x, y);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_bound) {
//            mobikeView.getmMobike().random();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
