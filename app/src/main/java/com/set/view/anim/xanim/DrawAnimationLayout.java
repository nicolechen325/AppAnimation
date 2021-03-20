package com.set.view.anim.xanim;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class DrawAnimationLayout extends LinearLayout {

    public static final String TAG = "DrawAnimationWidget";

    public DrawAnimationLayout(Context context) {
        super(context);
    }

    public DrawAnimationLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawAnimationLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawAnimationLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 子视图动画， 父视图调用 invalidate()方法，刷新区域。
     * parent.invalidate(l, t, r, b)
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    public void invalidate(int l, int t, int r, int b) {
        super.invalidate(l, t, r, b);
        Log.d("TAG_invalidate", "left:" + l + ",top:" + t + ",right:" + r + ",bottom:" + b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, Integer.toHexString(System.identityHashCode(this)) + " parent onDraw() method run.");
    }
}
