package com.set.ui.anim.xanim;

import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

/**
 * 帧动画
 */
public class MAnimationDrawable extends AnimationDrawable {

    //每一帧
    //setFrame -> selectDrawable -> 触发
    @Override
    public void invalidateSelf() {

        Drawable bitmapDrawable = getCurrent();
        Callback callback = getCallback();
        //callback 是 ImageView。
        super.invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {
        //由ImageView的onDraw方法，Drawable.draw 触发。
        //进而 mCurDrawable .draw，绘制当前Drawable帧
        super.draw(canvas);
    }


}
