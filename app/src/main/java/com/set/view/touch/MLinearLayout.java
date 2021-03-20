package com.set.view.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;

/**
 * 类似于ios的弹性效果。 重写LinearLayout,横向滚动
 */
public class MLinearLayout extends LinearLayout {
    public final String TAG = "ChenLinearLayout";
    private OverScroller mScroller;
    private int duration = 2000;
    private float mLastMotionX;
    private float mInitialMotionX;
    int lastX;
    private boolean mFakeDragging;
    private long mFakeDragBeginTime;
    private boolean mIsBeingDragged;
    private VelocityTracker mVelocityTracker;
    private int mScrollState = SCROLL_STATE_IDLE;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_SETTLING = 2;
    private VelocityTracker velocityTracker;
    private int downX;
    private int downY;

    public MLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
        setLongClickable(true);
        mScroller = new OverScroller(context);
    }

    public MLinearLayout(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);//
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightmode == MeasureSpec.AT_MOST) {
            Log.d("linearlayoutmy", "width:" + width + " height:" + height);
        } else if (heightmode == MeasureSpec.EXACTLY) {
            Log.d("linearlayoutmy", "width:" + width + " height:" + height);
        } else {
            Log.d("linearlayoutmy", "width:" + width + " height:" + height);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int pointerCount = event.getPointerCount();
        int idBits = 0;

        for (int i = 0; i < pointerCount; i++) {
            idBits |= 1 << event.getPointerId(i);
        }
        Log.d(TAG, "count=" + idBits);

        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                final int actionIndex = event.getActionIndex(); // alwa
                Log.d("linearmotion", "ACTION_DOWN=" + actionIndex + "==" + event.getPointerId(actionIndex));
                break;
            case MotionEvent.ACTION_MOVE:
                //setTranslationX((int)event.getX() - lastX );
                final int actionIndexm = event.getActionIndex(); // alwa
                Log.d("linearmotion", "ACTION_MOVE=" + actionIndexm + "==" + event.getPointerId(actionIndexm) + "===" + (int) event.getX() + "====" + event.getPointerCount());
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("linearmotion", "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("linearmotion", "ACTION_UP");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                final int actionIndex2 = event.getActionIndex(); // alwa
                Log.d("linearmotion", "ACTION_POINTER_DOWN=" + actionIndex2 + "==" + event.getPointerId(actionIndex2) + "===" + event.getPointerCount());
                break;
            case MotionEvent.ACTION_POINTER_UP:
                final int actionIndex3 = event.getActionIndex(); // alwa
                Log.d("linearmotion", "ACTION_POINTER_UP=" + actionIndex3 + "==" + event.getPointerId(actionIndex3) + "===" + event.getPointerCount());
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int action = MotionEventCompat.getActionMasked(ev);
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                Log.image_scale("linearmotion", "ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                Log.image_scale("linearmotion", "ACTION_POINTER_DOWN");
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                Log.image_scale("linearmotion", "ACTION_POINTER_UP");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.image_scale("linearmotion", "ACTION_MOVE");
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                Log.image_scale("linearmotion", "ACTION_CANCEL");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.image_scale("linearmotion", "ACTION_UP");
//                break;
//            default:
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = MotionEventCompat.getActionMasked(event);
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                final int actionIndex = event.getActionIndex(); // alwa
//               // downX = (int) event.getX();
//                downY = (int) event.getY();
//                 downX = (int) event.getRawX();
//                downY = (int) event.getRawY();
//               // Log.image_scale("linearmotion", "ACTION_DOWN_downX=" + downX + ",downY=" + downY);
//                initVelocityTrackerIfNotExists();
//                velocityTracker.addMovement(event);
//              //  Log.image_scale("linearmotion", "ACTION_DOWN="+actionIndex+"=="+event.getPointerId(actionIndex));
//                break;
//            case MotionEvent.ACTION_MOVE:
//                velocityTracker.addMovement(event);
//                velocityTracker.computeCurrentVelocity(100);
//                int moveX = (int) event.getRawX();
//                int moveY = (int) event.getRawY();
//               // Log.image_scale("linearmotion", "ACTION_DOWN_moveX=" + moveX + ",moveY=" + moveY);
//                //Log.image_scale("linearmotion", velocityTracker.getXVelocity() + "___ACTION_MOVE___" + velocityTracker.getYVelocity());
//              //  Log.image_scale("linearmotion", "ACTION_MOVE");
//                break;
//            case MotionEvent.ACTION_CANCEL:
//             //   Log.image_scale("linearmotion", "ACTION_CANCEL");
//                break;
//            case MotionEvent.ACTION_UP:
//                velocityTracker.addMovement(event);
//                int detalX = (int) event.getRawX() - downX;
//             //   Log.image_scale("linearmotion", "ACTION_UP");
//                // Log.image_scale("linearmotion", velocityTracker.getXVelocity() + "___ACTION_UP___" + velocityTracker.getYVelocity() + "===" + detalX);
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                final int actionIndex2 = event.getActionIndex(); // alwa
//              //  Log.image_scale("linearmotion", "ACTION_POINTER_DOWN="+actionIndex2+"=="+event.getPointerId(actionIndex2)+"==="+event.getPointerCount());
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                final int actionIndex3 = event.getActionIndex(); // alwa
//             //   Log.image_scale("linearmotion", "ACTION_POINTER_UP="+actionIndex3+"=="+event.getPointerId(actionIndex3)+"==="+event.getPointerCount());
//                break;
//            default:
//                break;
//        }
//        return super.onTouchEvent(event);
//    }

    private void initVelocityTrackerIfNotExists() {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
    }

    /**
     * 调用此方法滚动到目标位置
     * dy为目标位置和目前位置的差值 如果dy大于0.则是说明滚动条往下滚动。
     *
     * @param fx
     * @param fy
     */
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - getScrollX();
        int dy = fy - getScrollY();
        smoothScrollBy(dx, dy);
    }

    /**
     * 调用此方法设置滚动的相对偏移 这里dx为0，只在竖直方向滚动
     * 第一个参数是起始移动的x坐标值 第二个是起始移动的y坐标值 第三个第四个参数都是移动的偏移量 dy为负值 则是下拉，滚动条往上移动
     *
     * @param dx
     * @param dy
     */
    public void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(getScrollX(), getScrollY(), dx, dy, duration);
        ViewCompat.postInvalidateOnAnimation(this);// 这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {
        // 先判断mScroller滚动是否完成
        if (!mScroller.isFinished() && mScroller.computeScrollOffset()) {
            int oldX = getScrollX();
            int oldY = getScrollY();
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            if (oldX != x || oldY != y) {
                scrollTo(x, y);// 这里调用View的scrollTo()完成实际的滚动
            }
            Log.d(TAG, "computeScroll=" + y);
            // 必须调用该方法，否则不一定能看到滚动效果
            ViewCompat.postInvalidateOnAnimation(this);
        }
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                smoothScrollTo(0, 0);// 松手回退到原点
                break;
            case MotionEvent.ACTION_DOWN:
                mScroller.abortAnimation();
                mLastMotionX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                final float y = event.getY();
                final float x = event.getX();
                final float dx = mLastMotionX - event.getX();
                int dis = (int) ((dx - 0.5) / 2);
                mLastMotionX = x;
                float oldScrollX = getScrollX();
                float scrollX = oldScrollX + dis;
                if (scrollX != oldScrollX) {
                    scrollTo((int) scrollX, getScrollY());
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "ChenLinearLayout--onDraw---");
        super.onDraw(canvas);

    }
}

