package com.set.view.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Transformation;

public class MAlphaAnimation extends AlphaAnimation {
    public MAlphaAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MAlphaAnimation(float fromAlpha, float toAlpha) {
        super(fromAlpha, toAlpha);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
    }

    @Override
    public boolean willChangeBounds() {
        return super.willChangeBounds();
        // AlphaAnimation 默认 false ，其他 true
    }
}
