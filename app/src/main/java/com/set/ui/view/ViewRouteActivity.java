package com.set.ui.view;

import com.set.ui.BaseGridActivity;
import com.set.ui.RouteItemEntry;
import com.set.ui.view.canvas.ViewDrawCanvasActivity;
import com.set.ui.view.translation.ViewTranslationActivity;
import com.set.ui.view.viewpager2.ViewPager2Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * View Route
 */
public class ViewRouteActivity extends BaseGridActivity {

    @Override
    public List<RouteItemEntry> initItemData() {

        List<RouteItemEntry> list = new ArrayList<>();
        list.add(new RouteItemEntry(0, "Translation", ViewTranslationActivity.class));
        list.add(new RouteItemEntry(1, "Canvas", ViewDrawCanvasActivity.class));
        list.add(new RouteItemEntry(2, "ViewPage2", ViewPager2Activity.class));

        return list;
    }
}
