package com.set.view.scroll.viewholder;

import android.view.View;

import com.set.view.scroll.adapter.BaseAdapter;
import com.set.view.scroll.entity.MoreViewEntry;


public class ItemMoreViewHolder extends BaseRecycleViewHolder<MoreViewEntry> implements View.OnClickListener {
    MoreViewEntry moreViewEntry;

    public ItemMoreViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void setViewHolderData(MoreViewEntry object, int position, BaseAdapter.OnItemClickListener itemClickListener) {
        moreViewEntry = object;
    }

    @Override
    public void onClick(View v) {

        if (moreViewEntry.moreClickListener != null) {
            moreViewEntry.moreClickListener.onMoreClick(null);
        }
    }
}
