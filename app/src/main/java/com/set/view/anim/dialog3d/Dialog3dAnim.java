package com.set.view.anim.dialog3d;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.set.view.R;

/**
 * Dialog  3D
 */
public class Dialog3dAnim extends Dialog implements View.OnClickListener {
    private Context context;
    LinearLayout llFront;
    LinearLayout dialogContainer;
    LinearLayout llBack;
    private View view;

    private int centerX;
    private int centerY;
    private int depthZ = 700;
    private int duration = 300;
    private Rotate3dAnimation openAnimation;
    private Rotate3dAnimation closeAnimation;
    private boolean isOpen = false;

    public Dialog3dAnim(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.view_anim_dialog_3d, null);
        setContentView(view);
        llFront = (LinearLayout) view.findViewById(R.id.ll_front);
        dialogContainer = (LinearLayout) view.findViewById(R.id.dialog_container);
        llBack = (LinearLayout) view.findViewById(R.id.ll_back);
        llFront.setOnClickListener(this);
        llBack.setOnClickListener(this);
        //对话框窗体设置
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        lp.width = (int) (d.widthPixels * 0.8);
        lp.height = (int) (d.heightPixels * 0.6);
        dialogWindow.setAttributes(lp);
        setCancelable(true);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ll_back:
            case R.id.ll_front:
                startAnimation();
                break;
        }
    }

    private void startAnimation() {
        centerX = dialogContainer.getWidth() / 2;
        centerY = dialogContainer.getHeight() / 2;
        if (openAnimation == null) {
            initOpenAnim();
            initCloseAnim();
        }
        //动画在执行
        if (openAnimation.hasStarted() && !openAnimation.hasEnded()) {
            return;
        }
        if (closeAnimation.hasStarted() && !closeAnimation.hasEnded()) {
            return;
        }
        //判断执行哪个动画
        if (isOpen) {
            dialogContainer.startAnimation(openAnimation);
        } else {
            dialogContainer.startAnimation(closeAnimation);
        }
        isOpen = !isOpen;
    }

    private void initOpenAnim() {
        //从0到90度，顺时针旋转视图，此时reverse参数为true，达到90度时动画结束时视图变得不可见，
        openAnimation = new Rotate3dAnimation(0, 90, centerX, centerY, depthZ, true);
        openAnimation.setDuration(duration);
        openAnimation.setFillAfter(true);
        openAnimation.setInterpolator(new AccelerateInterpolator());
        openAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llBack.setVisibility(View.GONE);
                llFront.setVisibility(View.VISIBLE);
                //从270到360度，顺时针旋转视图，此时reverse参数为false，达到360度动画结束时视图变得可见
                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(270, 360, centerX, centerY, depthZ, false);
                rotateAnimation.setDuration(duration);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                dialogContainer.startAnimation(rotateAnimation);
            }
        });
    }

    private void initCloseAnim() {
        closeAnimation = new Rotate3dAnimation(360, 270, centerX, centerY, depthZ, true);
        closeAnimation.setDuration(duration);
        closeAnimation.setFillAfter(true);
        closeAnimation.setInterpolator(new AccelerateInterpolator());
        closeAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llBack.setVisibility(View.VISIBLE);
                llFront.setVisibility(View.GONE);
                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(90, 0, centerX, centerY, depthZ, false);
                rotateAnimation.setDuration(duration);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                dialogContainer.startAnimation(rotateAnimation);
            }
        });
    }
}