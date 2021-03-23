package com.set.ui;

import com.set.ui.anim.AnimRouteActivity;
import com.set.ui.image.ImageRouteActivity;
import com.set.ui.scroll.ScrollRouteActivity;
import com.set.ui.view.ViewRouteActivity;
import com.set.ui.surface.SurfaceRouteActivity;
import com.set.ui.touch.TouchEventActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class MainActivity extends BaseGridActivity {

    @Override
    public List<RouteItemEntry> initItemData() {
        List<RouteItemEntry> list = new ArrayList<>();
        list.add(new RouteItemEntry(0, R.drawable.icon_item, "Animation", AnimRouteActivity.class));
        list.add(new RouteItemEntry(1, R.drawable.icon_item, "Image", ImageRouteActivity.class));
        list.add(new RouteItemEntry(2, R.drawable.icon_item, "Scroll", ScrollRouteActivity.class));
        list.add(new RouteItemEntry(3, R.drawable.icon_item, "View", ViewRouteActivity.class));
        list.add(new RouteItemEntry(4, R.drawable.icon_item, "Surface", SurfaceRouteActivity.class));
        list.add(new RouteItemEntry(5, R.drawable.icon_item, "Touch", TouchEventActivity.class));
        return list;
    }
}
