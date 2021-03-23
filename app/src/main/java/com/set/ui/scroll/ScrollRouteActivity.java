package com.set.ui.scroll;

import com.set.ui.BaseGridActivity;
import com.set.ui.RouteItemEntry;
import com.set.ui.scroll.coordinator.CoordinatorActivity;
import com.set.ui.scroll.recyclerview.RecyclerViewActivity;
import com.set.ui.scroll.scrollview.ScrollViewActivity;
import com.set.ui.scroll.viewtype.RecyclerViewTypeActivity;

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
        list.add(new RouteItemEntry(1, "RecyclerViewType", RecyclerViewTypeActivity.class));
        list.add(new RouteItemEntry(2, "ScrollView", ScrollViewActivity.class));
        list.add(new RouteItemEntry(3, "RecyclerView", RecyclerViewActivity.class));

        return list;
    }
}
