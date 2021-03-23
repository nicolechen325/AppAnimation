package com.set.ui.scroll.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.set.ui.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewPagerViewHolder> {
    private List<Integer> mColors = new ArrayList<>();

    public RecyclerViewAdapter(List<Integer> colors) {
        this.mColors = colors;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        holder.mTvItem.setText("item " + position);
        holder.mRoot.setBackgroundResource(mColors.get(position));
    }

    @Override
    public int getItemCount() {
        return mColors.size();
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        TextView mTvItem;
        ConstraintLayout mRoot;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            mRoot = itemView.findViewById(R.id.root);
            mTvItem = itemView.findViewById(R.id.tv_rv_item);
        }
    }
}
