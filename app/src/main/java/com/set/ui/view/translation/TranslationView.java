package com.set.ui.view.translation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class TranslationView extends View {
    public static final String TAG_1 = "TView";
    private Paint mPaint;
    private boolean mEventRawXY = false;
    public int type = 0;
    private int lastX;
    private int lastY;
    private int mScrollX;
    private int mScrollY;
    private float mDownTranslationX;
    private float mDownTranslationY;

    private int sourceLeft;
    private int sourceTop;
    private int sourceRight;
    private int sourceBottom;

    OnLogPrint onLogPrint;

    public TranslationView(Context context) {
        super(context);
        init();
    }

    public TranslationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TranslationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(0xff33b5e5);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void useRawXY(boolean rawXY) {
        mEventRawXY = rawXY;
    }

    public void setMoveView(int type) {
        this.type = type;
        useRawXY(type == 4);

        lastX = 0;
        lastY = 0;
        mScrollX = 0;
        mScrollY = 0;
        mDownTranslationX = 0;
        mDownTranslationY = 0;

        //恢复位置
        Log.d(TAG_1 + "Reset",
                ":sourceLeft:" + sourceLeft +
                        ",sourceTop:" + sourceTop +
                        ",sourceRight:" + sourceRight +
                        ",sourceBottom:" + sourceBottom);
        layout(sourceLeft, sourceTop, sourceRight, sourceBottom);
        setScrollX(0);
        setScrollY(0);
        setTranslationX(0);
        setTranslationY(0);

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int rawx = (int) event.getRawX();
        final int rawy = (int) event.getRawY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = mEventRawXY ? rawx : x;
                lastY = mEventRawXY ? rawy : y;
                mDownTranslationX = getTranslationX();
                mDownTranslationY = getTranslationY();
                mScrollX = getScrollX();
                mScrollY = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                onMoveTouch(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        logAction(action, x, y, rawx, rawy);
        return true;
    }

    private void onMoveTouch(MotionEvent event) {
        final int x = (int) event.getX();//相对该视图坐标系
        final int y = (int) event.getY();
        final int rawx = (int) event.getRawX();//相对屏幕
        final int rawy = (int) event.getRawY();

        final int currentX = mEventRawXY ? rawx : x;
        final int currentY = mEventRawXY ? rawy : y;

        int offsetx = currentX - lastX;
        int offsety = currentY - lastY;


        switch (type) {
            case 0:
                layout(offsetx, offsety);
                break;
            case 1:
                offsetLeftAndRight(offsetx);  //对top和bottom偏移
                offsetTopAndBottom(offsety);
                break;
            case 2:
                layoutParams(offsetx, offsety);
                break;
            case 3:
                scroll(offsetx, offsety);
                break;
            case 4:
                translationXY(offsetx, offsety);
                break;
        }

        if (onLogPrint != null) {
            String logMsgMoreLine = "offsetx:" + offsetx + "\n" +
                    "offsety:" + offsety + "\n" +
                    "mLeft:" + getLeft() + "\n" +
                    "mTop:" + getTop() + "\n" +
                    "mRight:" + getRight() + "\n" +
                    "mBottom:" + getBottom() + "\n" +
                    "translationX:" + getTranslationX() + "\n" +
                    "translationY:" + getTranslationY() + "\n" +
                    "mScrollX:" + getScrollX() + "\n" +
                    "mScrollY:" + getScrollY();
            onLogPrint.onLogPrint(logMsgMoreLine);
        }

        String logMsg = ":offsetx:" + offsetx +
                ",offsety:" + offsety +
                ",mLeft:" + getLeft() +
                ",mTop:" + getTop() +
                ",mRight:" + getRight() +
                ",mBottom:" + getBottom() +
                ",translationX:" + getTranslationX() +
                ",translationY:" + getTranslationY() +
                ",mScrollX:" + getScrollX() +
                ",mScrollY:" + getScrollY();
        Log.d(TAG_1 + "onMove", logMsg);
    }

    void layout(int offsetx, int offsety) {
        //left、top、right、bottom基础上加上偏移量
        //改变 mLeft 和 mTop
        //需要 getX 和 getY
        //每次变化 offsetx ，累加到 mLeft 上，因为 mLeft变，currentX 是 相对视图坐标的，也跟着变，
        layout(getLeft() + offsetx,
                getTop() + offsety,
                getRight() + offsetx,
                getBottom() + offsety);
    }

    void layoutParams(int offsetx, int offsety) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.leftMargin += offsetx;
        layoutParams.topMargin += offsety;
        setLayoutParams(layoutParams);
    }

    void scroll(int offsetx, int offsety) {
        //视图内部滚动
        scrollTo(mScrollX - offsetx, mScrollY - offsety);
    }

    void translationXY(int offsetx, int offsety) {
        //需要 RawX 和 RawY
        setTranslationX(mDownTranslationX + offsetx);
        setTranslationY(mDownTranslationY + offsety);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        Log.d(TAG_1, "invalidate()");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //left,top: View 左上顶点相对于父容器的横坐标和纵坐标
        int mLeft = getLeft();
        int mTop = getTop();
        int mRight = getRight();
        int mBottom = getBottom();

        //mRight-mLeft,mBottomp-mTop
        int width = getWidth();
        int height = getHeight();

        //View 左上角相对父容器左上角的偏移量
        float translationX = getTranslationX();
        float translationY = getTranslationY();

        //View 左上角相对父容器左上角坐标，相比mLeft和mTop，差偏移量translation
        float X = getX();
        float Y = getY();

        //相对当前视图(0,0)位置Scroll
        int scrollX = getScrollX();
        int scrollY = getScrollY();

        //记录原始状态位置
        if (type == 0) {
            sourceLeft = getLeft();
            sourceTop = getTop();
            sourceRight = getRight();
            sourceBottom = getBottom();
        }

        Log.d(TAG_1 + "Draw",
                "mLeft:" + mLeft +
                        ",mTop:" + mTop +
                        ",mRight:" + mRight +
                        ",mBottom:" + mBottom +
                        ",width:" + width +
                        ",height:" + height +
                        ",translationX:" + translationX +
                        ",translationY:" + translationY +
                        ",scrollX:" + scrollX +
                        ",scrollY:" + scrollY);

        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawRect(rectF, mPaint);
    }

    void logAction(int action, int x, int y, int rawx, int rawy) {
        String actStr = null;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                actStr = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                actStr = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                actStr = "ACTION_UP";
                break;
        }
        Log.d(TAG_1 + "Touch", actStr + ":x:" + x + ",y:" + y + ",rawx:" + rawx + ",rawy:" + rawy);
    }

    public void setOnLogPrint(OnLogPrint onLogPrint) {
        this.onLogPrint = onLogPrint;
    }

    interface OnLogPrint {
        void onLogPrint(String msg);
    }
}
