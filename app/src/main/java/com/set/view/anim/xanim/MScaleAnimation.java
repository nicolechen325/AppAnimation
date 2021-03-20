package com.set.view.anim.xanim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;

public class MScaleAnimation extends ScaleAnimation {
    public MScaleAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MScaleAnimation(float fromX, float toX, float fromY, float toY) {
        super(fromX, toX, fromY, toY);
    }

    public MScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY) {
        super(fromX, toX, fromY, toY, pivotX, pivotY);
    }

    public MScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        super(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
    }





    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
    }
}
