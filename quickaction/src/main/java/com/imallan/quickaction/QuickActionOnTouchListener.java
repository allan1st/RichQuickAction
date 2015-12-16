package com.imallan.quickaction;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yilun
 * on 15/12/2015.
 */
public class QuickActionOnTouchListener implements View.OnTouchListener {

    private GestureDetector mGestureDetector;

    private QuickActionView mQuickActionView;

    private View mView;

    private Object mTag;

    GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.OnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            mQuickActionView.prepare(mTag);
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            mView.setPressed(false);
            mQuickActionView.show();
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    };

    public QuickActionOnTouchListener(Context context, QuickActionView quickActionsMenu) {
        this.mQuickActionView = quickActionsMenu;
        mGestureDetector = new GestureDetector(context, mOnGestureListener);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!mQuickActionView.isViewShown()) {
            mView = v;
            mTag = v.getTag();
            return mGestureDetector.onTouchEvent(event);
        }
        return false;
    }

}
