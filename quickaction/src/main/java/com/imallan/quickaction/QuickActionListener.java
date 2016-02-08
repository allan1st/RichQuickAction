package com.imallan.quickaction;

import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by yilun
 * on 15/12/2015.
 */
public interface QuickActionListener {

    void onViewPrepare(FrameLayout container, Object tag, List<Action> actions);

    void onViewShown(FrameLayout container);

    void onActionSelected(Action action, Object tag);

}
