package com.set.view;

import com.set.view.anim.AnimRouteActivity;
import com.set.view.image.ImageRouteActivity;
import com.set.view.scroll.ScrollRouteActivity;
import com.set.view.toolview.ViewTranslationActivity;
import com.set.view.surface.SurfaceRouteActivity;
import com.set.view.touch.TouchEventActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class MainActivity extends BaseGridViewActivity {

    @Override
    public List<RouteItemEntry> initItemData() {
        List<RouteItemEntry> list = new ArrayList<>();
        list.add(new RouteItemEntry(0, R.drawable.icon_item, "动画", AnimRouteActivity.class));
        list.add(new RouteItemEntry(1, R.drawable.icon_item, "Image", ImageRouteActivity.class));
        list.add(new RouteItemEntry(2, R.drawable.icon_item, "Scroll", ScrollRouteActivity.class));
        list.add(new RouteItemEntry(3, R.drawable.icon_item, "View", ViewTranslationActivity.class));
        list.add(new RouteItemEntry(4, R.drawable.icon_item, "Surface", SurfaceRouteActivity.class));
        list.add(new RouteItemEntry(5, R.drawable.icon_item, "Touch", TouchEventActivity.class));
        return list;
    }
}
