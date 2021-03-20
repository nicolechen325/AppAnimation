package com.set.view.scroll.entry;

public class Type1Entry {
    public long itemNum;
    public OnMoreClickListener moreClickListener;

    public interface OnMoreClickListener {
        public void onMoreClick(Object ob);
    }
}
