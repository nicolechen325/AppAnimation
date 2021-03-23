package com.set.ui.scroll.viewholder;

import android.view.View;

import com.set.ui.scroll.adapter.OnItemClickListener;
import com.set.ui.scroll.entry.Type1Entry;

public class ItemType1Holder extends BaseRecycleViewHolder<Type1Entry> implements View.OnClickListener {
    Type1Entry moreViewEntry;

    public ItemType1Holder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void setViewHolderData(Type1Entry object, int position, OnItemClickListener itemClickListener) {
        moreViewEntry = object;
    }

    @Override
    public void onClick(View v) {
        if (moreViewEntry.moreClickListener != null) {
            moreViewEntry.moreClickListener.onMoreClick(null);
        }
    }
}
