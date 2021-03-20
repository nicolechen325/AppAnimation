package com.set.view.scroll.entry;

import com.set.view.scroll.viewtype.FloorViewType;
import com.set.view.scroll.adapter.OnItemClickListener;

/**
 *
 */
public class ItemListEntry<T> {
    public T data;
    public FloorViewType floorType;
    public OnItemClickListener itemClickListener;
}
