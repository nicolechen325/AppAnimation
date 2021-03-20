package com.set.view.scroll;

import com.set.view.BaseGridViewActivity;
import com.set.view.RouteItemEntry;
import com.set.view.scroll.coordinator.CoordinatorActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Scroll
 */
public class ScrollRouteActivity extends BaseGridViewActivity {

    @Override
    public List<RouteItemEntry> initItemData() {

        List<RouteItemEntry> list = new ArrayList<>();
        list.add(new RouteItemEntry(0, "Coordinator", CoordinatorActivity.class));
        list.add(new RouteItemEntry(1, "RecyclerView", RecyclerViewTypeActivity.class));

        return list;
    }
}
