package com.set.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.set.view.scroll.viewtype.FloorViewType;
import com.set.view.scroll.adapter.AllViewTypeAdapter;
import com.set.view.scroll.adapter.OnItemClickListener;
import com.set.view.scroll.entry.ItemListEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 */
public abstract class BaseGridViewActivity extends AppCompatActivity {
    private List<ItemListEntry> mDataList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private AllViewTypeAdapter mainAdapter;
    protected List<RouteItemEntry> mBaseItemMap = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_base_grid);

        mRecyclerView = findViewById(R.id.main_base_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mBaseItemMap.addAll(initItemData());

        for (int m = 0; m < mBaseItemMap.size(); m++) {
            RouteItemEntry entry = mBaseItemMap.get(m);

            ItemListEntry<RouteItemEntry> itemListEntry = new ItemListEntry();
            itemListEntry.data = entry;
            itemListEntry.itemClickListener = new OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    onItemClick(position);
                }
            };
            itemListEntry.floorType = FloorViewType.ITEM_TYPE_ROUTE;
            mDataList.add(itemListEntry);
        }

        mainAdapter = new AllViewTypeAdapter(this, mDataList);
        mRecyclerView.setAdapter(mainAdapter);
    }

    public abstract List<RouteItemEntry> initItemData();

    void onItemClick(int position) {
        RouteItemEntry item = mBaseItemMap.get(position);
        Class toClass = item.mClass;
        toActivity(toClass);
    }

    void toActivity(Class<?> cls) {
        Intent intent = new Intent(BaseGridViewActivity.this, cls);
        startActivity(intent);
    }
}
