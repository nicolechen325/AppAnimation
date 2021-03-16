package com.set.view.scroll.entity;


import com.set.view.scroll.FloorType;
import com.set.view.scroll.adapter.BaseAdapter;

/**
 *
 */
public class ItemEntry<T> {
    public T data;
    public FloorType floorType;
    public BaseAdapter.OnItemClickListener itemClickListener;
}
