package com.set.view.scroll;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.set.view.R;
import com.set.view.scroll.adapter.AllViewTypeAdapter;
import com.set.view.scroll.adapter.OnItemClickListener;
import com.set.view.scroll.entity.Bean;
import com.set.view.scroll.entity.ItemListEntry;
import com.set.view.scroll.entity.MoreViewEntry;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RecyclerViewTypeActivity extends AppCompatActivity {
    private List<ItemListEntry> mTopDataList = new ArrayList<>();
    private List<ItemListEntry> mDataList = new ArrayList<>();
    private RecyclerView mTopRecyclerView;
    private RecyclerView mRecyclerView;
    AllViewTypeAdapter buAdapter;
    AllViewTypeAdapter topAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycler_viewtype_scroll);

        mTopRecyclerView = findViewById(R.id.recyclerview_top);
        mRecyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager topLinearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 3);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mTopRecyclerView.setLayoutManager(topLinearLayoutManager);


        initData();
    }

    private void initData() {
        //top
        ItemListEntry<MoreViewEntry> itemsEntry = new ItemListEntry();
        itemsEntry.data = new MoreViewEntry();
        itemsEntry.floorType = FloorViewType.ITEM_TYPE_1;
        mTopDataList.add(itemsEntry);

        ItemListEntry<Bean> itemsEntry2 = new ItemListEntry();
        itemsEntry2.data = new Bean();
        itemsEntry2.floorType = FloorViewType.ITEM_TYPE_2;
        mTopDataList.add(itemsEntry2);

        ItemListEntry<Bean> itemsEntry3 = new ItemListEntry();
        itemsEntry3.data = new Bean();
        itemsEntry3.floorType = FloorViewType.ITEM_TYPE_5;
        mTopDataList.add(itemsEntry3);

        topAdapter = new AllViewTypeAdapter(this, mTopDataList);
        mTopRecyclerView.setAdapter(topAdapter);

        //content
        for (int i = 0; i < 10; i++) {
            ItemListEntry<Bean> itemsEntry0 = new ItemListEntry();
            itemsEntry0.data = new Bean();
            itemsEntry0.itemClickListener = new OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    Toast.makeText(RecyclerViewTypeActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
                }

            };
            itemsEntry0.floorType = FloorViewType.ITEM_TYPE_5;
            mDataList.add(itemsEntry0);
        }

        buAdapter = new AllViewTypeAdapter(this, mDataList);
        mRecyclerView.setAdapter(buAdapter);

        buAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
            }
        });
    }

}