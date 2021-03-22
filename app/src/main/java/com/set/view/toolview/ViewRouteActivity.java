package com.set.view.toolview;

import com.set.view.BaseGridActivity;
import com.set.view.RouteItemEntry;
import com.set.view.toolview.canvas.ViewDrawCanvasActivity;
import com.set.view.toolview.translation.ViewTranslationActivity;

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

        return list;
    }
}
