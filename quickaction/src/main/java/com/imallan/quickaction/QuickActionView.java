package com.imallan.quickaction;

/**
 * Created by yilun
 * on 16/12/2015.
 */
public interface QuickActionView {

    void prepare();

    void show();

    void hide();

    boolean isViewShown();
}

