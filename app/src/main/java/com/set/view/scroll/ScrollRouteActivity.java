package com.set.view.scroll;

import com.set.view.BaseGridActivity;
import com.set.view.RouteItemEntry;
import com.set.view.scroll.coordinator.CoordinatorActivity;
import com.set.view.scroll.scrollview.ScrollViewActivity;
import com.set.view.scroll.viewtype.RecyclerViewTypeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Scroll
 */
public class ScrollRouteActivity extends BaseGridActivity {

    @Override
    public List<RouteItemEntry> initItemData() {

        List<RouteItemEntry> list = new ArrayList<>();
        list.add(new RouteItemEntry(0, "Coordinator", CoordinatorActivity.class));
        list.add(new RouteItemEntry(1, "RecyclerView", RecyclerViewTypeActivity.class));
        list.add(new RouteItemEntry(1, "ScrollView", ScrollViewActivity.class));

        return list;
    }
}
