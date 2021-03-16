package com.set.view.scroll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.set.view.scroll.FloorType;
import com.set.view.R;
import com.set.view.scroll.entity.ItemEntry;
import com.set.view.scroll.entity.MoreViewEntry;
import com.set.view.scroll.entity.Type3Entity;
import com.set.view.scroll.viewholder.BaseRecycleViewHolder;
import com.set.view.scroll.viewholder.ImageViewHolder;
import com.set.view.scroll.viewholder.Item3Holder;
import com.set.view.scroll.viewholder.ItemExpandedHolder;
import com.set.view.scroll.viewholder.ItemMoreViewHolder;
import com.set.view.scroll.viewholder.TitleViewHolder;

import java.util.List;

/**
 * @cg
 */
public class BuAdapter extends BaseAdapter {

    public BuAdapter(Context context, List<ItemEntry> dataList) {
        super(context, dataList);
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseRecycleViewHolder viewHolder = createCustomViewHolder(parent, viewType);
        if (viewHolder != null) {
            return viewHolder;
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        bindCustomViewholderData(holder, position);
    }


    @Override
    public void addData(ItemEntry data) {
        super.addData(data);
        mDataList.add(data);
        notifyItemInserted(mDataList.size());
    }

    @Override
    public void removeLastView() {
        super.removeLastView();
    }


    /**
     * 按照下标添加数据
     *
     * @param data
     * @param index
     */
    public void updateData(ItemEntry data, int index) {
        if (mDataList != null) {
            if (mDataList.size() != 0 && mDataList.get(index).floorType.ordinal() == data.floorType.ordinal()) {
                mDataList.set(index, data);
                notifyItemChanged(index);
            } else {
                mDataList.add(index, data);
                notifyItemInserted(index);
            }
        }
    }

    /**
     * 创建自己的业务viewholder
     *
     * @param parent
     * @param viewType
     * @return
     */
    private BaseRecycleViewHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FloorType.ITEM_TYPE_1.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_more, parent, false);
            return new ItemMoreViewHolder(view);
        } else if (viewType == FloorType.ITEM_TYPE_2.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_txt, parent, false);
            return new TitleViewHolder(view);
        } else if (viewType == FloorType.ITEM_TYPE_3.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_type3, parent, false);
            return new Item3Holder(view);
        } else if (viewType == FloorType.ITEM_TYPE_4.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_expandanim, parent, false);
            return new ItemExpandedHolder(view);
        } else if (viewType == FloorType.ITEM_TYPE_5.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_image, parent, false);
            return new ImageViewHolder(view);
        } else {
            return null;
        }
    }

    /**
     * 为自定义的viewholder绑定自己的数据
     *
     * @param holder
     * @param position
     */
    private void bindCustomViewholderData(BaseRecycleViewHolder holder, int position) {

        ItemEntry itemsEntry = mDataList.get(position);
        if (holder instanceof ItemMoreViewHolder) {

            MoreViewEntry entry = (MoreViewEntry) itemsEntry.data;
            holder.setViewHolderData(entry, position,itemsEntry.itemClickListener);

        } else if (holder instanceof Item3Holder) {
            Type3Entity entry = (Type3Entity) itemsEntry.data;
            holder.setViewHolderData(entry, position,itemsEntry.itemClickListener);
        }
    }
}
