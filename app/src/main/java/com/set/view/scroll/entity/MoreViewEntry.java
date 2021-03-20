package com.set.view.scroll.entity;

public class MoreViewEntry {
    public long itemNum;
    public OnMoreClickListener moreClickListener;

    public interface OnMoreClickListener {
        public void onMoreClick(Object ob);
    }
}
