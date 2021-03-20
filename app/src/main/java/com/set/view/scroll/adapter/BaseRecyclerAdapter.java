package com.set.view.scroll.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;

import com.set.view.scroll.entry.ItemListEntry;
import com.set.view.scroll.viewholder.BaseRecycleViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView Adapter
 */
public class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecycleViewHolder> {
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    protected Context mContext;
    protected List<ItemListEntry> mDataList = new ArrayList<>();
    protected int mDataTotal;

    public BaseRecyclerAdapter(Context context, List<ItemListEntry> dataList) {
        mContext = context;
        mDataList = dataList;
        mDataTotal = 0;
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int i) {
    }

    @Override
    public int getItemCount() {
        if (mDataList == null) {
            mDataTotal = 0;
            return 0;
        }
        mDataTotal = mDataList.size();
        return mDataTotal;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList != null && mDataList.size() > position && position >= 0) {
            return mDataList.get(position).floorType.ordinal();
        } else {
            return super.getItemViewType(position);
        }
    }

    /**
     * 添加分割线
     * 不需要自动添加分割线
     *
     * @param data
     */
    public void addData(ItemListEntry data) {
        if (mDataList == null) {
            return;
        }

        int total = mDataList.size();

        if (total == 0) {
            return;
        }
    }

    public void removeLastView() {
        if (mDataList == null) {
            return;
        }
        if (mDataList.size() == 0) {
            return;
        }
        mDataList.remove(mDataList.size() - 1);
        notifyItemRemoved(mDataList.size() - 1);
    }

    /**
     * 清空所有的搜索结果
     */
    public void clearAllData() {
        if (mDataList != null && mDataList.size() != 0) {
            mDataList.clear();
        }
        mDataTotal = 0;
        notifyDataSetChanged();
    }

}