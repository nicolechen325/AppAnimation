package com.set.ui.scroll.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

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
