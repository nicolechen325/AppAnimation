package com.set.ui.scroll.entry;

import com.set.ui.scroll.viewtype.FloorViewType;
import com.set.ui.scroll.adapter.OnItemClickListener;

/**
 *
 */
public class ItemListEntry<T> {
    public T data;
    public FloorViewType floorType;
    public OnItemClickListener itemClickListener;
}
