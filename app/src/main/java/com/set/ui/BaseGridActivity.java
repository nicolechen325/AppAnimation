package com.set.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.set.ui.scroll.adapter.RecycleViewDivider;
import com.set.ui.scroll.viewtype.FloorViewType;
import com.set.ui.scroll.adapter.AllViewTypeAdapter;
import com.set.ui.scroll.adapter.OnItemClickListener;
import com.set.ui.scroll.entry.ItemListEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 */
public abstract class BaseGridActivity extends AppCompatActivity {
    private List<ItemListEntry> mDataList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private AllViewTypeAdapter mainAdapter;
    protected List<RouteItemEntry> routeItemEntries = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_base_grid);

        mRecyclerView = findViewById(R.id.main_base_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //分割线
        mRecyclerView.addItemDecoration(new RecycleViewDivider(2, getResources().getColor(R.color.color_d1)));

        routeItemEntries.addAll(initItemData());

        for (int m = 0; m < routeItemEntries.size(); m++) {
            RouteItemEntry entry = routeItemEntries.get(m);

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
        RouteItemEntry item = routeItemEntries.get(position);
        Class toClass = item.mClass;
        toActivity(toClass);
    }

    void toActivity(Class<?> cls) {
        Intent intent = new Intent(BaseGridActivity.this, cls);
        startActivity(intent);
    }
}
