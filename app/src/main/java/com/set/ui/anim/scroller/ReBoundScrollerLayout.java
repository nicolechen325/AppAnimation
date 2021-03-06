package com.set.ui.anim.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

/**
 * Scroller控件
 */
public class ReBoundScrollerLayout extends LinearLayout {
    private final String TAG_05 = "ReBoundScrollerLayout";
    private Scroller mScroller;
    private int mLastMotionY;

    public ReBoundScrollerLayout(Context context) {
        super(context);
        init(context);
    }

    public ReBoundScrollerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ReBoundScrollerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setClickable(true);
        setLongClickable(true);
        mScroller = new Scroller(context);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    /**
     * 比较简单的实现，若内部控件太多，需要实现打断方法
     * 这里只说明原理。
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int actionMasked = ev.getActionMasked();

        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN: {
                mLastMotionY = (int) ev.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE:
                final int y = (int) ev.getY();
                int deltaY = mLastMotionY - y;//deltaY
                Log.d(TAG_05, deltaY + "");
                int disY = (int) (2 * (deltaY - 0.5) / 3);
                //scrollBy(0, disY);
                beginScroll(0, disY);
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_UP:
                reset(0, 0);
                break;
        }
        return true;
    }

    protected void reset(int x, int y) {
        //int dy = y - getScrollY();
        //mScroller.startScroll(0, getScrollY(), 0, dy, 2000);
        // invalidate();
        int dy = y - mScroller.getFinalY();
        beginScroll(0, dy);
    }

    protected void beginScroll(int dx, int dy) {
        //dy小于0时，会导致mScrollY小于0，内容向下滚动
        //mScrollY>0，内容向上滚动
        mScroller.startScroll(0, mScroller.getFinalY(), 0, dy, 2000);
        invalidate();
    }

}
