package com.set.anim.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, Integer.toHexString(System.identityHashCode(this)) + " parent onDraw() method run.");
    }
}
