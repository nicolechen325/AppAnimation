package com.set.view.scroll.viewholder;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.set.view.R;
import com.set.view.scroll.adapter.OnItemClickListener;
import com.set.view.scroll.entry.Type4Entry;

public class ItemType4Holder extends BaseRecycleViewHolder<Type4Entry> implements View.OnClickListener {
    Type4Entry entity;

    TextView animView;

    public ItemType4Holder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        animView = itemView.findViewById(R.id.tv_expanded);
        int width = animView.getWidth();
        Activity activity = (Activity) mContext;
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int x = point.x;
        int y = point.y;

        Log.d("recycleranimation", width + "-=");
        animView.setPivotX(x);
        animView.setPivotY(0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(animView, "scaleY", 0f, 1f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(animView, "scaleX", 0f, 1f);

        rotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//               Object object =
                float v = (Float) animation.getAnimatedValue();
                Log.d("recycleranimation", v + "");
                int height = (int) (200 * v);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
                animView.setLayoutParams(layoutParams);
            }
        });

        final AnimatorSet animationSet = new AnimatorSet();
        animationSet.setDuration(400);

        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.play(animator).with(rotate);


//        animView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                animationSet.start();
//            }
//        },5000);

        animationSet.start();
    }

    @Override
    public void setViewHolderData(Type4Entry object, int position, OnItemClickListener itemClickListener) {
        this.entity = object;


    }

    @Override
    public void onClick(View v) {


    }
}
