package com.set.view.toolview.translation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TranslationView extends View {
    public static final String TAG_1 = "TranslationView";
    private Paint mPaint;
    private int lastX;
    private int lastY;

    private int mScrollX;
    private int mScrollY;

    private float mTranslationX;
    private float mTranslationY;

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

    public TranslationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int rawx = (int) event.getRawX();
        final int rawy = (int) event.getRawY();
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG_1 + "Touch", "ACTION_DOWN:x:" + x + ",y:" + y + ",rawx:" + rawx + ",rawy:" + rawy);
                lastX = x;
                lastY = y;
//                lastX = rawx;
//                lastY = rawy;
                mTranslationX = getTranslationX();
                mTranslationY = getTranslationY();
                mScrollX = getScrollX();
                mScrollY = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG_1 + "Touch", "ACTION_MOVE:x:" + x + ",y:" + y + ",rawx:" + rawx + ",rawy:" + rawy);
                onMoveTouch(event);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG_1 + "Touch", "ACTION_UP:x:" + x + ",y:" + y + ",rawx:" + rawx + ",rawy:" + rawy);
                break;
        }
        return true;
    }


    private void onMoveTouch(MotionEvent event) {
        final int currentX = (int) event.getX();//相对该视图坐标系
        final int currentY = (int) event.getY();

//        final int currentX = (int) event.getRawX();//相对屏幕
//        final int currentY = (int) event.getRawY();
        int offsetx = currentX - lastX;
        int offsety = currentY - lastY;

        //left、top、right、bottom基础上加上偏移量
        //改变 mLeft 和 mTop
        //需要 getX 和 getY
        // 每次变化 offsetx ，累加到 mLeft 上，因为 mLeft变，currentX 是 相对视图坐标的，也跟着变，
        layout(getLeft() + offsetx,
                getTop() + offsety,
                getRight() + offsetx,
                getBottom() + offsety);

        //视图内部滚动
//        scrollTo(mScrollX - offsetx, mScrollY - offsety);
        Log.d(TAG_1 + "onMove", ":offsetx:" + offsetx + ",offsety:" + offsety +
                ",mLeft:" + getLeft() +
                ",mTop:" + getTop() +
                ",mRight:" + getRight() +
                ",mBottom:" + getBottom() +
                ",translationX:" + getTranslationX() +
                ",translationY:" + getTranslationY());

        //需要 RawX 和 RawY
//        setTranslationX(mTranslationX + offsetx);
//        setTranslationY(mTranslationY + offsety);
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

        Log.d(TAG_1 + "Draw", "onDraw:" +
                ",mLeft:" + mLeft +
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


}
