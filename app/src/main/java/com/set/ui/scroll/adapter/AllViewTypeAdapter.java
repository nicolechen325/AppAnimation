package com.set.ui.scroll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.set.ui.RouteItemEntry;
import com.set.ui.scroll.viewtype.FloorViewType;
import com.set.ui.R;
import com.set.ui.scroll.entry.Type2Entry;
import com.set.ui.scroll.entry.Type3Entry;
import com.set.ui.scroll.entry.Type4Entry;
import com.set.ui.scroll.entry.Type5Entry;
import com.set.ui.scroll.entry.ItemListEntry;
import com.set.ui.scroll.entry.Type1Entry;
import com.set.ui.scroll.viewholder.BaseRecycleViewHolder;
import com.set.ui.scroll.viewholder.ItemType5Holder;
import com.set.ui.scroll.viewholder.ItemRouteHolder;
import com.set.ui.scroll.viewholder.ItemType3Holder;
import com.set.ui.scroll.viewholder.ItemType1Holder;
import com.set.ui.scroll.viewholder.ItemType2Holder;
import com.set.ui.scroll.viewholder.ItemType4Holder;

import java.util.List;

/**
 * @cg
 */
public class AllViewTypeAdapter extends BaseRecyclerAdapter {

    public AllViewTypeAdapter(Context context, List<ItemListEntry> dataList) {
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
    public void addData(ItemListEntry data) {
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
    public void updateData(ItemListEntry data, int index) {
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
        if (viewType == FloorViewType.ITEM_TYPE_1.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_type1, parent, false);
            return new ItemType1Holder(view);
        } else if (viewType == FloorViewType.ITEM_TYPE_2.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_type2, parent, false);
            return new ItemType2Holder(view);
        } else if (viewType == FloorViewType.ITEM_TYPE_3.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_type3, parent, false);
            return new ItemType3Holder(view);
        } else if (viewType == FloorViewType.ITEM_TYPE_4.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_type4, parent, false);
            return new ItemType4Holder(view);
        } else if (viewType == FloorViewType.ITEM_TYPE_5.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_type5, parent, false);
            return new ItemType5Holder(view);
        } else if (viewType == FloorViewType.ITEM_TYPE_ROUTE.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_route, parent, false);
            return new ItemRouteHolder(view);
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

        ItemListEntry itemsEntry = mDataList.get(position);
        if (holder instanceof ItemType1Holder) {
            Type1Entry entry = (Type1Entry) itemsEntry.data;
            holder.setViewHolderData(entry, position, itemsEntry.itemClickListener);
        } else if (holder instanceof ItemType2Holder) {
            Type2Entry entry = (Type2Entry) itemsEntry.data;
            holder.setViewHolderData(entry, position, itemsEntry.itemClickListener);
        } else if (holder instanceof ItemType3Holder) {
            Type3Entry entry = (Type3Entry) itemsEntry.data;
            holder.setViewHolderData(entry, position, itemsEntry.itemClickListener);
        } else if (holder instanceof ItemType4Holder) {
            Type4Entry entry = (Type4Entry) itemsEntry.data;
            holder.setViewHolderData(entry, position, itemsEntry.itemClickListener);
        } else if (holder instanceof ItemType5Holder) {
            Type5Entry entry = (Type5Entry) itemsEntry.data;
            holder.setViewHolderData(entry, position, itemsEntry.itemClickListener);
        } else if (holder instanceof ItemRouteHolder) {
            RouteItemEntry entry = (RouteItemEntry) itemsEntry.data;
            holder.setViewHolderData(entry, position, itemsEntry.itemClickListener);
        }
    }
}
