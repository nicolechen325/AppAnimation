package com.set.view.scroll.viewtype;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.set.view.R;
import com.set.view.scroll.adapter.AllViewTypeAdapter;
import com.set.view.scroll.adapter.OnItemClickListener;
import com.set.view.scroll.entry.Type2Entry;
import com.set.view.scroll.entry.Type3Entry;
import com.set.view.scroll.entry.Type4Entry;
import com.set.view.scroll.entry.Type5Entry;
import com.set.view.scroll.entry.ItemListEntry;
import com.set.view.scroll.entry.Type1Entry;

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
    AllViewTypeAdapter topAdapter;
    AllViewTypeAdapter buAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycler_viewtype_scroll);

        mTopRecyclerView = findViewById(R.id.recyclerview_top);
        mRecyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager topLinearLayoutManager = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mTopRecyclerView.setLayoutManager(topLinearLayoutManager);

        initData();
    }

    private void initData() {
        //top
        ItemListEntry<Type1Entry> itemsEntry = new ItemListEntry();
        itemsEntry.data = new Type1Entry();
        itemsEntry.floorType = FloorViewType.ITEM_TYPE_1;
        mTopDataList.add(itemsEntry);

        ItemListEntry<Type2Entry> itemsEntry2 = new ItemListEntry();
        itemsEntry2.data = new Type2Entry();
        itemsEntry2.floorType = FloorViewType.ITEM_TYPE_2;
        mTopDataList.add(itemsEntry2);

        ItemListEntry<Type3Entry> itemsEntry3 = new ItemListEntry();
        itemsEntry3.data = new Type3Entry();
        itemsEntry3.floorType = FloorViewType.ITEM_TYPE_3;
        mTopDataList.add(itemsEntry3);

        topAdapter = new AllViewTypeAdapter(this, mTopDataList);
        mTopRecyclerView.setAdapter(topAdapter);

        //content
        ItemListEntry<Type4Entry> itemsEntry4 = new ItemListEntry();
        itemsEntry4.data = new Type4Entry();
        itemsEntry4.floorType = FloorViewType.ITEM_TYPE_4;
        mDataList.add(itemsEntry4);

        for (int i = 0; i < 10; i++) {
            ItemListEntry<Type5Entry> itemsEntry0 = new ItemListEntry();
            itemsEntry0.data = new Type5Entry();
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