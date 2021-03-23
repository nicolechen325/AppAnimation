package com.set.ui.view.viewpager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.set.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPage2
 */
public class ViewPager2Activity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_3 = "ViewPager2Activity";
    private ViewPager2 viewPager2;
    private List<Integer> mColors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_viewpager2);
        initView();
    }

    protected void initView() {
        mColors.add(R.color.blue_light);
        mColors.add(R.color.red_light);
        mColors.add(R.color.orange_light);
        mColors.add(R.color.green_light);

        viewPager2 = findViewById(R.id.vp2);
        ViewPager2Adapter viewPagerAdapter = new ViewPager2Adapter(mColors);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);//竖直滚动

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d(TAG_3, "ViewPager onPageSelected " + position);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
    }

}
