package com.set.ui.scroll.viewholder;

import android.view.View;
import android.widget.TextView;

import com.set.ui.R;
import com.set.ui.scroll.adapter.OnItemClickListener;
import com.set.ui.scroll.entry.Type3Entry;

public class ItemType3Holder extends BaseRecycleViewHolder<Type3Entry> implements View.OnClickListener {
    Type3Entry entity;
    TextView animView;

    public ItemType3Holder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        animView = itemView.findViewById(R.id.tv_expanded);
    }

    @Override
    public void setViewHolderData(Type3Entry object, int position, OnItemClickListener itemClickListener) {
        this.entity = object;
    }

    @Override
    public void onClick(View v) {


    }
}
