package com.set.view.scroll.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.set.view.R;
import com.set.view.scroll.adapter.BaseAdapter;
import com.set.view.scroll.entity.TitleEntry;

public class TitleViewHolder extends BaseRecycleViewHolder<TitleEntry> implements View.OnClickListener {
    private String TAG = "SubTitleViewHolder";

    private TextView item_tag_tv;

    public TitleViewHolder(View itemView) {
        super(itemView);
        item_tag_tv = (TextView) itemView.findViewById(R.id.item_tag_tv);
    }

    @Override
    public void setViewHolderData(TitleEntry object, int position, BaseAdapter.OnItemClickListener itemClickListener) {
        if (!TextUtils.isEmpty(object.mTitle)) {
            item_tag_tv.setText(object.mTitle);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
