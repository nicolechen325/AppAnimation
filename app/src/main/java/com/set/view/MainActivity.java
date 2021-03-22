package com.set.view;

import com.set.view.anim.AnimRouteActivity;
import com.set.view.image.ImageRouteActivity;
import com.set.view.scroll.ScrollRouteActivity;
import com.set.view.toolview.ViewRouteActivity;
import com.set.view.surface.SurfaceRouteActivity;
import com.set.view.touch.TouchEventActivity;

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
