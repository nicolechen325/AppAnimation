package com.set.view.scroll.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 */
public class AppLayoutBehavior extends AppBarLayout.Behavior {

    public AppLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        RecyclerView recyclerView = (RecyclerView) target;
        if (dyConsumed == 0 && (dyUnconsumed < 0 || dyUnconsumed > 0) && type == ViewCompat.TYPE_NON_TOUCH) {
            recyclerView.stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
        }
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }
}
