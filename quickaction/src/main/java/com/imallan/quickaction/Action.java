package com.imallan.quickaction;

import android.content.Context;

/**
 * Created by yilun
 * on 15/12/2015.
 */
public class Action {

    Context mContext;

    String mActionText;

    int mIconResId;

    int mActionId;

    public Action(Context context, String actionText, int iconResId, int actionId) {
        mContext = context;
        mActionText = actionText;
        mIconResId = iconResId;
        mActionId = actionId;
    }

    public int getActionId() {
        return mActionId;
    }
}
