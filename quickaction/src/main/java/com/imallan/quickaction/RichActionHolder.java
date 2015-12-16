package com.imallan.quickaction;

import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

/**
 * Created by yilun
 * on 16/12/2015.
 */
public abstract class RichActionHolder implements QuickActionListener {

    private QuickActionOnTouchListener mQuickActionOnTouchListener;

    private ArrayList<Action> mActions;

    private RichActionView mRichActionView;

    public RichActionHolder(ArrayList<Action> actions) {
        mActions = actions;
    }

    public void bindRichActionView(View view, RichActionView richActionView) {
        mRichActionView = richActionView;
        mQuickActionOnTouchListener = new QuickActionOnTouchListener(view.getContext(), mRichActionView);
        view.setOnTouchListener(mQuickActionOnTouchListener);
        mRichActionView.setQuickActionListener(this);
    }

    @Override
    public void onViewPrepare(FrameLayout container, Object tag) {
        mRichActionView.clearActions();
        if (mActions != null) {
            for (Action action : mActions) {
                mRichActionView.addAction(action);
            }
        }
    }
}
