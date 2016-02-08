package com.imallan.quickaction;

import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by yilun
 * on 16/12/2015.
 */
public abstract class RichActionHolder implements QuickActionListener {

    private QuickActionOnTouchListener mQuickActionOnTouchListener;

    private RichActionView mRichActionView;

    public void bindRichActionView(View view, RichActionView richActionView, List<Action> actions) {
        view.setTag(R.id.rich_action_tag_actions, actions);
        mRichActionView = richActionView;
        mQuickActionOnTouchListener = new QuickActionOnTouchListener(view.getContext(), mRichActionView);
        view.setOnTouchListener(mQuickActionOnTouchListener);
        mRichActionView.setQuickActionListener(this);
    }

    @Override
    public void onViewPrepare(FrameLayout container, Object tag, List<Action> actions) {
        mRichActionView.clearActions();
        if (actions != null) {
            for (Action action : actions) {
                mRichActionView.addAction(action);
            }
        }
    }
}
