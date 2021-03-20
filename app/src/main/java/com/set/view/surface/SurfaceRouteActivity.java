package com.set.view.surface;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.set.view.R;
import com.set.view.surface.video.TextureVideoActivity;

/**
 * Surface  View
 */
public class SurfaceRouteActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_surface_route);

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_1) {
            startActivity(new Intent(SurfaceRouteActivity.this, SurfaceHolderActivity.class));
        } else if (v.getId() == R.id.btn_2) {
            startActivity(new Intent(SurfaceRouteActivity.this, TextureViewActivity.class));

        } else if (v.getId() == R.id.btn_3) {
            startActivity(new Intent(SurfaceRouteActivity.this, SurfaceViewActivity.class));

        } else if (v.getId() == R.id.btn_4) {
            startActivity(new Intent(SurfaceRouteActivity.this, TextureVideoActivity.class));

        }
    }

}
