package com.set.view;

import java.io.Serializable;

public class EntryItem implements Serializable {
    public int position;
    public String mDesc;
    public Class mClass;

    public EntryItem(int position, String mDesc, Class mClass) {
        this.position = position;
        this.mDesc = mDesc;
        this.mClass = mClass;
    }
}
