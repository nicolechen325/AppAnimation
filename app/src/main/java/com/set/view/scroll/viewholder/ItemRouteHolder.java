package com.set.view.scroll.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.set.view.R;
import com.set.view.RouteItemEntry;
import com.set.view.scroll.adapter.OnItemClickListener;

/**
 *
 */
public class ItemRouteHolder extends BaseRecycleViewHolder<RouteItemEntry> implements View.OnClickListener {
    protected RouteItemEntry viewHolderData;
    private TextView mTextView;
    private ImageView mIconView;
    private int position;

    private OnItemClickListener onItemClickListener;

    public ItemRouteHolder(View view) {
        super(view);
        mContext = view.getContext();

        mTextView = view.findViewById(R.id.tv_item);
        mIconView = view.findViewById(R.id.img_itemicon);
    }

    @Override
    public void setViewHolderData(RouteItemEntry itemData, int position, OnItemClickListener itemClickListener) {
        viewHolderData = itemData;

        if (viewHolderData == null) {
            return;
        }

        if (viewHolderData.mResId != 0) {
            mIconView.setImageResource(viewHolderData.mResId);
        }
        String itemText = viewHolderData.mText;
        mTextView.setText(itemText);


        this.position = position;
        onItemClickListener = itemClickListener;

    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onClick(position);
        }
    }
}