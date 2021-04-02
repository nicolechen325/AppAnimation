package com.set.ui.anim.xanim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * 帧动画 ImageView
 */
public class AnimationImageView extends AppCompatImageView {

    public static final String TAG = "AnimationImageView";

    public AnimationImageView(Context context) {
        super(context);
    }

    public AnimationImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //帧动画，根据时间间隔绘制
        Log.d(TAG, "child onDraw() method run.");
    }

    @Override
    public void invalidateDrawable( Drawable drawable) {
        //这里会触发invalidate。
      super.invalidateDrawable(drawable);
    }
}
