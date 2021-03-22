package com.set.view.toolview.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Canvas 绘制
 */
public class DrawCanvasView extends View {
    private final String TAG_02 = "DrawCanvasView";
    private Paint mPaint;

    public DrawCanvasView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public DrawCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    public DrawCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(255, 255, 250, 177);
        drawRect(canvas);
    }

    private void drawRect(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        Log.d(TAG_02, "width:" + width + ",height:" + height);

        //drawRect
        mPaint.setColor(0xffaafdc0);//A:ff,R:8b,G:c5,B:ba
        int left2 = width / 3 * 2;
        int top2 = 10;
        int right2 = width - 30;
        int bottom2 = height / 3;
        canvas.drawRect(left2, top2, right2, bottom2, mPaint);

        //drawPoint
        canvas.save();
        int x = width / 2;
        int translateY = height / 3;
        int y = translateY / 2;
        mPaint.setColor(0xff8bc5ba);//设置颜色
        mPaint.setStrokeWidth(50);//设置线宽，如果不设置线宽，无法绘制点
        canvas.translate(0, translateY);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(x, y, mPaint);
        canvas.restore();

        //drawOval
        float left = 10;
        float top = 0;
        float right = width / 3;
        float bottom = height / 3;
        RectF rectF = new RectF(left, top, right, bottom);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(0xff8bc5ba);
        canvas.drawOval(rectF, mPaint);

        //drawLine
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(0xffff0000);
        int startX = width / 3;
        canvas.drawLine(startX, 100, startX + 200, 100, mPaint);
        canvas.drawLine(startX, 300, startX + 300, 300, mPaint);

        //drawCircle
        int halfCanvasWidth = width / 2;
        int D = height / 3;
        int R = D / 2;
        canvas.save();
        canvas.translate(0, height / 3);
        canvas.drawCircle(halfCanvasWidth, R, R, mPaint);
        canvas.restore();

        //drawArc
        int count = 5;
        float ovalHeight = height / 6;
        float left3 = 10;
        float top3 = height * 2 / 3;
        float right3 = width / 2 - left;
        float bottom3 = top3 + ovalHeight;
        RectF rectF3 = new RectF(left3, top3, right3, bottom3);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(0xff8bc5ba);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF3, 0, 120, true, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xff0000ff);
        canvas.drawArc(rectF3, 0, 120, true, mPaint);

        drawAxis(canvas);

    }

    //坐标系，以左上角原点
    private void drawAxis(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);

        mPaint.setColor(0xff33b5e5);
        drawXYAxis(canvas);

        canvas.translate(canvasWidth / 4, canvasWidth / 4);
        drawXYAxis(canvas);

        canvas.translate(canvasWidth / 4, canvasWidth / 4);
        canvas.rotate(60);//基于当前绘图坐标系的原点旋转坐标系
        drawXYAxis(canvas);
    }

    void drawXYAxis(Canvas canvas) {
        mPaint.setColor(0xff33b5e5);
        canvas.drawLine(0, 0, canvas.getWidth(), 0, mPaint);//x轴
        mPaint.setColor(0xffff4444);
        canvas.drawLine(0, 0, 0, canvas.getHeight(), mPaint);
    }
}
