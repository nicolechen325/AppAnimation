package com.set.ui.anim.xanim;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;

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
