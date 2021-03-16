package com.set.view.scroll.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.set.view.scroll.adapter.BaseAdapter;

/**
 * @cg
 */
public abstract class BaseRecycleViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected Context mContext;

    public BaseRecycleViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mContext = itemView.getContext();
    }

    public abstract void setViewHolderData(T object, int position, BaseAdapter.OnItemClickListener onItemClickListener);

}