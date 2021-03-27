package com.set.ui.anim.xanim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;

public class MRotateAnimation extends RotateAnimation {

    public MRotateAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MRotateAnimation(float fromDegrees, float toDegrees) {
        super(fromDegrees, toDegrees);
    }

    public MRotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY) {
        super(fromDegrees, toDegrees, pivotX, pivotY);
    }

    public MRotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        super(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if (transforiLog != null) {
            transforiLog.onTransLog(interpolatedTime);
        }
    }
    TransforiLog transforiLog;

    public void setTransforiLog(TransforiLog transforiLog) {
        this.transforiLog = transforiLog;
    }
}
