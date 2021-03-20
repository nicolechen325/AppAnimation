package com.set.view.scroll.viewholder;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.set.view.scroll.adapter.OnItemClickListener;

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

    public abstract void setViewHolderData(T object, int position, OnItemClickListener onItemClickListener);
}
