package com.set.ui.scroll.recyclerview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.set.ui.R;
import com.set.ui.scroll.adapter.AllViewTypeAdapter;
import com.set.ui.scroll.adapter.OnItemClickListener;
import com.set.ui.scroll.entry.ItemListEntry;
import com.set.ui.scroll.entry.Type1Entry;
import com.set.ui.scroll.entry.Type2Entry;
import com.set.ui.scroll.entry.Type3Entry;
import com.set.ui.scroll.entry.Type4Entry;
import com.set.ui.scroll.entry.Type5Entry;
import com.set.ui.scroll.viewtype.FloorViewType;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView
 */
public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<Integer> mColors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recyclerview);
        mRecyclerView = findViewById(R.id.recyclerview_source);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        initData();
    }

    private void initData() {

        mColors.add(R.color.blue_light);
        mColors.add(R.color.red_light);
        mColors.add(R.color.orange_light);
        mColors.add(R.color.green_light);
        mColors.add(R.color.light_orange);
        mColors.add(R.color.dark_orange);
        mColors.add(R.color.purple_start);
        mColors.add(R.color.purple_end);
        mColors.add(R.color.green_start);
        mColors.add(R.color.green_end);
        mColors.add(R.color.blue_start);
        mColors.add(R.color.blue_end);
        mColors.add(R.color.purple_light);

        mAdapter = new RecyclerViewAdapter(mColors);
        mRecyclerView.setAdapter(mAdapter);

    }
}