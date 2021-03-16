package com.set.view.scroll.viewholder;

import android.view.View;
import android.widget.TextView;

import com.set.view.R;
import com.set.view.scroll.adapter.BaseAdapter;
import com.set.view.scroll.entity.Type3Entity;

/**
 *
 */
public class Item3Holder extends BaseRecycleViewHolder<Type3Entity> implements View.OnClickListener {
    protected Type3Entity viewHolderData;
    private TextView textView;

    private int position;

    private BaseAdapter.OnItemClickListener onItemClickListener;

    public Item3Holder(View view) {
        super(view);
        mContext = view.getContext();

        textView = view.findViewById(R.id.tv_item);
    }

    @Override
    public void setViewHolderData(Type3Entity itemData, int position, BaseAdapter.OnItemClickListener itemClickListener) {
        viewHolderData = itemData;
        textView.setText(itemData.itemText);
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