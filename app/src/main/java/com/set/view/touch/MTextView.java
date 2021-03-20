package com.set.view.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

/**
 *
 */
public class MTextView extends AppCompatTextView {
    static final String TAG = "ChenTextView";

    public MTextView(Context context) {
        super(context);
        init(null);
    }

    public MTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public void init(AttributeSet attrs) {

    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        int action = MotionEventCompat.getActionMasked(event);
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                final int actionIndex = event.getActionIndex(); // alwa
//                Log.image_scale(TAG, "MotionEventTextView_ACTION_DOWN=" + actionIndex + "==" + event.getPointerId(actionIndex));
//                break;
//            case MotionEvent.ACTION_MOVE:
//                final int actionIndexm = event.getActionIndex(); // alwa
//                Log.image_scale(TAG, "MotionEventTextView_ACTION_MOVE=" + actionIndexm + "==" + event.getPointerId(actionIndexm) + "===" + event.getPointerCount());
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                Log.image_scale(TAG, "MotionEventTextView_ACTION_CANCEL");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.image_scale(TAG, "MotionEventTextView_ACTION_UP");
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                final int actionIndex2 = event.getActionIndex(); // alwa
//                Log.image_scale(TAG, "MotionEventTextView_ACTION_POINTER_DOWN=" + actionIndex2 + "==" + event.getPointerId(actionIndex2) + "===" + event.getPointerCount());
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                final int actionIndex3 = event.getActionIndex(); // alwa
//                Log.image_scale(TAG, "MotionEventTextView_ACTION_POINTER_UP=" + actionIndex3 + "==" + event.getPointerId(actionIndex3) + "===" + event.getPointerCount());
//                break;
//            default:
//                break;
//        }
//
//        return super.dispatchTouchEvent(event);
//    }


    //打印绘制Log
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.image_scale(TAG, "ChenTextView--onDraw---");
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        String str = Integer.toHexString(System.identityHashCode(this));
        boolean flag = super.onTouchEvent(ev);
        final int actionMasked = ev.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, flag + "---onTouchEvent方法" + "---ACTION_DOWN---" + str);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, flag + "---onTouchEvent方法" + "---ACTION_MOVE---" + str);
                this.scrollBy(0, 1);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, flag + "---onTouchEvent方法" + "---ACTION_UP---" + str);
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, flag + "---onTouchEvent方法" + "---ACTION_CANCEL---" + str);
                break;
        }
        return flag;
//        return super.onTouchEvent(ev);
    }
}
