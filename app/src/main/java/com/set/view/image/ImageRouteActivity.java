package com.set.view.image;

import com.set.view.BaseGridActivity;
import com.set.view.RouteItemEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Image入口
 */
public class ImageRouteActivity extends BaseGridActivity {

    @Override
    public List<RouteItemEntry> initItemData() {
        List<RouteItemEntry> list = new ArrayList<>();

        list.add(new RouteItemEntry(0, "Image Scale", ImageScaleActivity.class));
        list.add(new RouteItemEntry(1, "NinePng", NinePngActivity.class));
        return list;
    }

}
