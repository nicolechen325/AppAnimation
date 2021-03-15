package com.set.view.anim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

public class MTranslateAnimation extends TranslateAnimation {
    public MTranslateAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MTranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        super(fromXDelta, toXDelta, fromYDelta, toYDelta);
    }

    public MTranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue) {
        super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
    }
}
