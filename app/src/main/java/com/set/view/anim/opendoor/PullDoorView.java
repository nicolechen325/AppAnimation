package com.set.view.anim.opendoor;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.set.view.R;

/**
 * 上滑开门效果效果
 * 重写RelativeLayout 内部实现一个ImageView 增加
 * Scroller。是RelativeLayout内部空间网上滑动，RelativeLayout背景透明。
 */
public class PullDoorView extends RelativeLayout {
    private static final String TAG = "PULLDOOR_VIEW";
    private Context mContext;
    private Scroller mScroller;
    private int mScreenHeigh = 0;
    private int mLastDownY = 0;
    private int mCurryY;
    private int mDelY;
    private boolean mCloseFlag = false;
    private ImageView mImgView;
    private GestureDetector mGestureDetector;

    PullDoorCallBack mCallback;

    public PullDoorView(Context context) {
        super(context);
        init(context);
    }

    public PullDoorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mGestureDetector = new GestureDetector(context,
                new PullDoorGestureListener());
        // 有弹跳效果的Interpolator
        Interpolator polator = new BounceInterpolator();
        mScroller = new Scroller(mContext, polator);

        // 获取屏幕分辨
        WindowManager wm = (WindowManager) (mContext
                .getSystemService(Context.WINDOW_SERVICE));
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeigh = dm.heightPixels;

        this.setBackgroundColor(Color.argb(0, 0, 0, 0));// 设置背景透明色
        mImgView = new ImageView(mContext);
        mImgView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        mImgView.setScaleType(ImageView.ScaleType.FIT_XY);// 填充整个屏幕
        mImgView.setImageResource(R.drawable.bg_door); // 默认背景
        addView(mImgView);
    }

    // 推动门的动画
    public void startBounceAnim(int startY, int dy, int duration) {
        mScroller.startScroll(0, startY, 0, dy, duration);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                mCurryY = (int) event.getY();
                mDelY = mCurryY - mLastDownY;
                if (mDelY < 0) {
                    if (Math.abs(mDelY) > mScreenHeigh / 2) {
                        // 向上滑动超过半个屏幕高的时向上消失动画
                        startBounceAnim(this.getScrollY(), mScreenHeigh, 450);
                        mCloseFlag = true;
                        if (mCallback != null) {
                            mCallback.onPullDoorSuccess();
                        }
                    } else {
                        // 向上滑动未超过半个屏幕高的时向下弹动动画
                        startBounceAnim(this.getScrollY(), -this.getScrollY(), 1000);
                    }
                } else {
                    smoothScrollBy(0, -mScroller.getFinalY());
                }
                break;
            default:
                return mGestureDetector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 不要忘记更新界面
            postInvalidate();
        } else {
            if (mCloseFlag) {
                this.setVisibility(View.GONE);
            }
        }
    }

    class PullDoorGestureListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            mLastDownY = (int) e.getY();
            Log.d(TAG, "ACTION_DOWN=" + mLastDownY);
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            //滑动手势事件。 手势往下 distanceY为负值。 手势往上 distanceY为正值
            int dis = (int) distanceY;
            Log.d(TAG, "distanceY=" + distanceY);
            // disTotal+=dis;
            if (dis > 0) {// dis>0说明手势上移。
                smoothScrollBy(0, dis);
            } else {
                /**
                 * 手势下移时 先判断滚动条最后的位置是否还处在>0的状态
                 * 在接近等于0的时候。需要判断mScroller的最后位置和dis的绝对值大小。去最小的那个
                 * 防止下滑使mScroller.getFinalY()小于0
                 */
                if (mScroller.getFinalY() > 0) {
                    Log.d(TAG, "final =" + mScroller.getFinalY() + "----dis ="
                            + dis);
                    int minDis = mScroller.getFinalY() < Math.abs(dis) ? -mScroller
                            .getFinalY() : dis;
                    smoothScrollBy(0, minDis);
                }
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            return false;
        }
    }

    public void smoothScrollBy(int dx, int dy) {
        // 设置mScroller的滚动偏移量
        /**
         * 第一个参数是起始移动的x坐标值 第二个是起始移动的y坐标值 第三个第四个参数都是移到某点的坐标值 dy为负值 则是下拉，滚动条往上移动
         * dy为正值 则是上推，滚动条往下移动
         */
        mScroller.startScroll(0, mScroller.getFinalY(), dx, dy);
        invalidate();// 这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

   public interface PullDoorCallBack {
        void onPullDoorSuccess();
    }

    public void setmCallback(PullDoorCallBack mCallback) {
        this.mCallback = mCallback;
    }
}
