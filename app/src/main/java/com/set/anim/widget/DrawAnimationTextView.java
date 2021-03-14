package com.set.anim.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.set.animation.R;

public class DrawAnimationTextView extends AppCompatTextView {

    public static final String TAG = "DrawAnimationWidget";

    public DrawAnimationTextView(Context context) {
        super(context);
    }

    public DrawAnimationTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawAnimationTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "child onDraw() method run.");
    }
}
