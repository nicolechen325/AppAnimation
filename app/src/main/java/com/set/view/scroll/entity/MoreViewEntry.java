package com.set.view.scroll.entity;



public class MoreViewEntry {
    //打点使用
    public String keywordCate;
    //数量
    public long itemNum;
    public OnMoreClickListener moreClickListener;

    public interface OnMoreClickListener {
        public void onMoreClick(Object ob);
    }
}
