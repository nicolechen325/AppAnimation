
package com.set.ui.scroll.pulltorefresh.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;

import com.set.ui.R;
import com.set.ui.scroll.pulltorefresh.PullToRefreshBase;


public class PillLoadingLayout extends LoadingLayout {

    public PillLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
    }

    @Override
    protected void setBackgroundDrawable(Context context) {
        super.setBackgroundDrawable(context);
        if (null == context || null == mHeaderImageBackground) return;
       // mHeaderImageBackground.setImageDrawable(context.getResources().getDrawable(R.mipmap.pill_loading_bg));
    }

    @Override
    protected int getVerticalLayout() {
        return R.layout.pull_to_refresh_header_vertical_pill;
    }

    @Override
    protected int getVerticalContentSize() {
        float refreshImageHeight = getResources().getDimension(R.dimen.pill_ptr_content_refresh_height);
        return (int)(refreshImageHeight);
    }

    @Override
    protected void refreshingRelayout() {
        int width = getResources().getDimensionPixelOffset(R.dimen.pill_ptr_image_bg_width);
        int height = getResources().getDimensionPixelOffset(R.dimen.pill_ptr_image_bg_height);
        LayoutParams layoutParams = new LayoutParams(width,height);
        int marginTop = - getResources().getDimensionPixelOffset(R.dimen.pill_ptr_content_refresh_hide_height);
        layoutParams.setMargins(0,marginTop,0,0);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        mHeaderImageBackground.setLayoutParams(layoutParams);
    }

    @Override
    protected void resetRefreshingRelayout() {
        int width = getResources().getDimensionPixelOffset(R.dimen.pill_ptr_image_bg_width);
        int height = getResources().getDimensionPixelOffset(R.dimen.pill_ptr_image_bg_height);
        LayoutParams layoutParams = new LayoutParams(width,height);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        mHeaderImageBackground.setLayoutParams(layoutParams);
    }

    @Override
    protected void resetImpl() {
        mHeaderImage.clearAnimation();
        resetImageRotation();
    }

    @Override
    protected void pullToRefreshImpl() {
        if (mUseIntrinsicAnimation) {
            ((AnimationDrawable) mHeaderImage.getDrawable()).stop();
        }
    }

    /**
     * 若松手即可刷新的位置状态上的实现
     */
    @Override
    protected void releaseToRefreshImpl() {
        if (mUseIntrinsicAnimation) {
            ((AnimationDrawable) mHeaderImage.getDrawable()).start();
        }
    }

    private void resetImageRotation() {
    }

    public void onLoadingDrawableSet(Drawable imageDrawable) {
    }

    protected void onPullImpl(float scaleOfLayout) {
    }

    @Override
    protected void refreshingImpl() {
    }

    @Override
    protected int getDefaultDrawableResId() {
//        return R.drawable.pill_loading_layout;
        return 0;
    }

    @Override
    public void setLastUpdatedLabel(CharSequence label) {
    }
}
