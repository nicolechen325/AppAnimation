package com.set.view.image;

import com.set.view.BaseGridViewActivity;
import com.set.view.EntryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Image入口
 */
public class ImageRouteActivity extends BaseGridViewActivity {

    @Override
    public List<EntryItem> initItemData() {
        List<EntryItem> list = new ArrayList<>();

        list.add(new EntryItem(0, "Image Scale", ImageScaleActivity.class));
        list.add(new EntryItem(1, "NinePng", NinePngActivity.class));
        return list;
    }

}
