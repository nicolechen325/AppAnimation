package com.set.ui.scroll.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.set.ui.R;
import com.set.ui.scroll.adapter.OnItemClickListener;
import com.set.ui.scroll.entry.Type2Entry;

public class ItemType2Holder extends BaseRecycleViewHolder<Type2Entry> implements View.OnClickListener {
    private String TAG = "SubTitleViewHolder";

    private TextView item_tag_tv;

    public ItemType2Holder(View itemView) {
        super(itemView);
        item_tag_tv =  itemView.findViewById(R.id.item_tag_tv);
    }

    @Override
    public void setViewHolderData(Type2Entry object, int position, OnItemClickListener itemClickListener) {
        if (!TextUtils.isEmpty(object.mTitle)) {
            item_tag_tv.setText(object.mTitle);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
