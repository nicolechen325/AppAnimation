package com.set.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.set.view.scroll.FloorType;
import com.set.view.scroll.adapter.BaseAdapter;
import com.set.view.scroll.adapter.BuAdapter;
import com.set.view.scroll.entity.ItemEntry;
import com.set.view.scroll.entity.Type3Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 */
public abstract class BaseGridViewActivity extends AppCompatActivity {
    private List<ItemEntry> mDataList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private BuAdapter mainAdapter;
    protected List<EntryItem> mBaseItemMap = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_base_grid);

        mRecyclerView = findViewById(R.id.main_base_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mBaseItemMap.addAll(initItemData());

        for (int m = 0; m < mBaseItemMap.size(); m++) {
            ItemEntry<Type3Entity> itemsEntry0 = new ItemEntry();
            itemsEntry0.data = new Type3Entity(mBaseItemMap.get(m).mDesc);
            itemsEntry0.itemClickListener = new BaseAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    onItemClick(position);
                }
            };
            itemsEntry0.floorType = FloorType.ITEM_TYPE_3;
            mDataList.add(itemsEntry0);
        }

        mainAdapter = new BuAdapter(this, mDataList);
        mRecyclerView.setAdapter(mainAdapter);
    }

    public abstract List<EntryItem> initItemData();

    void onItemClick(int position) {
        EntryItem item = mBaseItemMap.get(position);

        Class toClass = item.mClass;
        toActivity(toClass);
    }

    void toActivity(Class<?> cls) {
        Intent intent = new Intent(BaseGridViewActivity.this, cls);
        startActivity(intent);
    }
}
