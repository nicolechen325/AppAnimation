package com.set.ui.scroll.viewholder;

import android.view.View;

import com.set.ui.scroll.adapter.OnItemClickListener;
import com.set.ui.scroll.entry.Type5Entry;

/**
 *
 */
public class ItemType5Holder extends BaseRecycleViewHolder<Type5Entry> implements View.OnClickListener {
    Type5Entry data;

    public ItemType5Holder(View view) {
        super(view);
        mContext = view.getContext();
    }

    @Override
    public void setViewHolderData(Type5Entry itemData, int position, OnItemClickListener itemClickListener) {
        data = itemData;
    }

    @Override
    public void onClick(View v) {
    }
}