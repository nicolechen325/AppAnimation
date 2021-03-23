package com.set.ui.scroll.coordinator;

import com.google.android.material.tabs.TabLayout;

/**
 * Tab Select Listener
 */
public interface OnTabSelectedListener {
    void onTabSelected(TabLayout.Tab tab);

    void onTabUnselected(TabLayout.Tab tab);

    void onTabReselected(TabLayout.Tab tab);
}
