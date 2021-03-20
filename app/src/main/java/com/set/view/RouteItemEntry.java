package com.set.view;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;

import java.io.Serializable;

public class RouteItemEntry implements Serializable {
    public int position;
    @DrawableRes
    public int mResId;
    public String mText;
    public Class mClass;

    public RouteItemEntry(int position, String mText, Class mClass) {
        this.position = position;
        this.mText = mText;
        this.mClass = mClass;
    }

    public RouteItemEntry(int position, @DrawableRes int mResId, String mText, Class mClass) {
        this.position = position;
        this.mResId = mResId;
        this.mText = mText;
        this.mClass = mClass;
    }
}
