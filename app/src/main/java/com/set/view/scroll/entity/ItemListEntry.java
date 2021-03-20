package com.set.view.scroll.entity;

import com.set.view.scroll.FloorViewType;
import com.set.view.scroll.adapter.OnItemClickListener;

/**
 *
 */
public class ItemListEntry<T> {
    public T data;
    public FloorViewType floorType;
    public OnItemClickListener itemClickListener;
}
