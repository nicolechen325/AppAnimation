package com.set.view.scroll.coordinator;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.set.view.R;

import java.util.ArrayList;

/**
 * CoordinatorLayout 视图
 */
public class CoordinatorActivity extends AppCompatActivity {
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"推荐", "关注", "财经", "热榜"};

    private ViewPager mViewPager;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private ActionBar mActionbar;
    private TabLayout mTabLayout;
    private ImageView mImageView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_coordinator_layout);
        initFragments();
        initViewPager();

        mImageArray = new int[]{
                R.drawable.bg_tab_header_1,
                R.drawable.bg_tab_header_2,
                R.drawable.bg_tab_header_3,
                R.drawable.bg_tab_header_4};

        mColorArray = new int[]{
                R.color.blue_light,
                R.color.red_light,
                R.color.orange_light,
                R.color.green_light};

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mActionbar = getSupportActionBar();
        mCollapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlayout);
        mTabLayout = findViewById(R.id.tabLayout);
        mImageView = findViewById(R.id.img_header_view);

        mCollapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(
                CoordinatorActivity.this, R.color.colorPrimary));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(
                CoordinatorActivity.this, R.color.white));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(ContextCompat.getColor(
                CoordinatorActivity.this, R.color.white)));

        mCoordinatorLayout = findViewById(R.id.coordinatorlayout);
        setTranslucentStatusBar(this);
        if (mActionbar != null) {
            mActionbar.setTitle("CoordinatorLayout");
        }
        if (mActionbar != null) {
            mActionbar.setDisplayHomeAsUpEnabled(true);
            mActionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        }
        setupTabLayout();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupTabLayout() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mImageView.startAnimation(AnimationUtils.loadAnimation(CoordinatorActivity.this, R.anim.anim_coordinator_header_dismiss));
                if (mImageArray != null) {
                    mImageView.setImageResource(mImageArray[tab.getPosition()]);
                }
                if (mColorArray != null) {
                    mCollapsingToolbarLayout.setContentScrimColor(
                            ContextCompat.getColor(
                                    CoordinatorActivity.this, mColorArray[tab.getPosition()]));
                }
                mImageView.setAnimation(AnimationUtils.loadAnimation(CoordinatorActivity.this, R.anim.anim_coordinator_header_show));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(CoordTabFragment.getInstance(title));
        }
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.vp);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }

    public void setTranslucentStatusBar(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow()
                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (mToolbar != null) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mToolbar.getLayoutParams();
            layoutParams.setMargins(
                    layoutParams.leftMargin,
                    layoutParams.topMargin + SystemView.getStatusBarHeight(activity),
                    layoutParams.rightMargin,
                    layoutParams.bottomMargin);
        }
    }
}
