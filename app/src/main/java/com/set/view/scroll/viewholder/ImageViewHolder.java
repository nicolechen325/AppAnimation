package com.set.view.scroll.viewholder;

import android.view.View;

import com.set.view.scroll.adapter.BaseAdapter;
import com.set.view.scroll.entity.Bean;

/**
 *
 */
public class ImageViewHolder extends BaseRecycleViewHolder<Bean> implements View.OnClickListener {
    Bean data;

    public ImageViewHolder(View view) {
        super(view);
        mContext = view.getContext();
    }

    @Override
    public void setViewHolderData(Bean itemData, int position, BaseAdapter.OnItemClickListener itemClickListener) {
        data = itemData;
    }

    @Override
    public void onClick(View v) {

    }
}