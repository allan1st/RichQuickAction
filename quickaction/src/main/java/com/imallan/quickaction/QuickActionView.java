package com.imallan.quickaction;

import java.util.List;

/**
 * Created by yilun
 * on 16/12/2015.
 */
public interface QuickActionView {

    void prepare(Object tag, List<Action> actions);

    void show(Object tag);

    void hide();

    boolean isViewShown();
}

