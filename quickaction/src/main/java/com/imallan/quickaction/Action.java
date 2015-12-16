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

    public Callback mCallback;

    public Action(Context context, String actionText, int iconResId, Callback callback) {
        mContext = context;
        mActionText = actionText;
        mIconResId = iconResId;
        mCallback = callback;
    }

    public interface Callback {
        void call();
    }

}
