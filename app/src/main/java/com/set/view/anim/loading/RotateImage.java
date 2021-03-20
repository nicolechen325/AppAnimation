package com.set.view.anim.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.set.view.R;

public class RotateImage extends AppCompatImageView {
    //转动频率
    private static final int INCREMENT = 30;
    // 延迟时间
    private static final int DELAY_TIME = 100;
    //处理器
    private Handler mHandler;
    //是否停止
    private boolean flagStop;
    private int msgCount;
    private int arc;

    public RotateImage(Context context) {
        super(context);
        init();
    }

    public RotateImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        /*
         * if (visibility == View.VISIBLE) { start(); } else { flagStop = true;
         * }
         */
    }

    public void stop() {
        flagStop = true;
    }

    public void start() {
        flagStop = false;
        if (msgCount == 0) {
            mHandler.sendEmptyMessage(0);
            msgCount++;
        }
    }

    private void init() {
        if (getDrawable() == null) {
            setImageDrawable(getResources()
                    .getDrawable(R.drawable.progress_img));
        }
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                msgCount--;
                if (!flagStop) {
                    invalidate();//自动清屏并屏幕刷新,调用onDraw方法
                    arc = (arc + INCREMENT) % 360;
                    sendEmptyMessageDelayed(0, DELAY_TIME);
                    msgCount++;
                }
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(arc, canvas.getClipBounds().width() / 2, canvas
                .getClipBounds().height() / 2);
        super.onDraw(canvas);
    }
}
