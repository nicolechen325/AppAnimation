package com.set.view;

import com.set.view.anim.AnimRouteActivity;
import com.set.view.image.ImageRouteActivity;
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
    public List<EntryItem> initItemData() {
        List<EntryItem> list = new ArrayList<>();
        list.add(new EntryItem(0, "动画", AnimRouteActivity.class));
        list.add(new EntryItem(1, "Image", ImageRouteActivity.class));
        list.add(new EntryItem(2, "View", ViewTranslationActivity.class));
        list.add(new EntryItem(3, "Surface", SurfaceRouteActivity.class));
        list.add(new EntryItem(3, "Touch", TouchEventActivity.class));
        return list;
    }
}
