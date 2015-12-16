package com.imallan.quickaction;

import android.widget.FrameLayout;

/**
 * Created by yilun
 * on 15/12/2015.
 */
public interface QuickActionListener {

    void onViewPrepare(FrameLayout container, Object tag);

    void onViewShown(FrameLayout container);

    void onActionSelected(Action action);

}
