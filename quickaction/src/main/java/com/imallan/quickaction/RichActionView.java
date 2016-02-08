package com.imallan.quickaction;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yilun
 * on 15/12/2015.
 */
public class RichActionView extends FrameLayout implements QuickActionView {

    private static final String TAG = "RichActionView";

    private Interpolator mInterpolator;

    private LinearLayout mLinearLayoutContainer;

    private FrameLayout mFrameLayoutCustomContent;

    private LinearLayout mLinearLayoutActions;

    private QuickActionListener mQuickActionListener;

    private boolean isViewShown;

    private List<Action> mActionList = new ArrayList<>();

    private List<View> mActionIconList = new ArrayList<>();

    private int[] mLocation = new int[2];

    private int mActionSelected = -1;

    private TextView mTextViewActionLabel;

    private Object mTag;

    public RichActionView(Context context) {
        super(context);
        init();
    }

    public RichActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RichActionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setQuickActionListener(QuickActionListener quickActionListener) {
        mQuickActionListener = quickActionListener;
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.rich_action_view_layout_content, this, true);
        mLinearLayoutContainer = (LinearLayout) findViewById(R.id.rich_action_view_container);
        mLinearLayoutActions = (LinearLayout) findViewById(R.id.rich_action_view_actions);
        mFrameLayoutCustomContent = (FrameLayout) findViewById(R.id.rich_action_view_content);
        mTextViewActionLabel = (TextView) findViewById(R.id.rich_action_text_view_action);
        mInterpolator = new DecelerateInterpolator();
        setAlpha(0F);
    }

    public void prepare(Object tag, List<Action> actions) {
        //set width
        if (mLinearLayoutContainer.getContext().getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            mLinearLayoutContainer.getLayoutParams().width = (int) (getWidth() / 2.5F);
        } else {
            mLinearLayoutContainer.getLayoutParams().width
                    = LayoutParams.MATCH_PARENT;
        }
        mLinearLayoutContainer.requestLayout();
        if (mQuickActionListener != null) {
            mQuickActionListener.onViewPrepare(mFrameLayoutCustomContent, tag, actions);
        }
    }

    public void show(Object tag) {
        mTag = tag;
        isViewShown = true;
        mLinearLayoutContainer.setVisibility(VISIBLE);
        mLinearLayoutContainer.setScaleX(0.8F);
        mLinearLayoutContainer.setScaleY(0.8F);
        mLinearLayoutContainer.animate().setInterpolator(mInterpolator)
                .scaleX(1)
                .scaleY(1)
                .setListener(null)
                .setDuration(300)
                .start();
        animate().alpha(1F).start();
    }

    public void hide() {
        if (mActionSelected >= 0) {
            if (mQuickActionListener != null) {
                mQuickActionListener.onActionSelected(mActionList.get(mActionSelected), mTag);
            }
        }
        mLinearLayoutContainer.animate().setInterpolator(mInterpolator)
                .scaleX(.8F)
                .scaleY(.8F)
                .setDuration(200)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLinearLayoutContainer.setVisibility(INVISIBLE);
                        isViewShown = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        mLinearLayoutContainer.setVisibility(INVISIBLE);
                        isViewShown = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
        animate().alpha(0F).start();
    }

    @Override
    public boolean isViewShown() {
        return isViewShown;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isViewShown()) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                hide();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                boolean mActionTriggered = false;
                for (int i = 0; i < mActionList.size(); i++) {
                    View icon = mActionIconList.get(i);
                    icon.getLocationOnScreen(mLocation);
                    if (y > mLocation[1] && y < (mLocation[1] + icon.getHeight()) &&
                            x > mLocation[0] && x < (mLocation[0] + icon.getWidth())) {
                        if (mActionSelected != i) {
                            icon.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                            mActionSelected = i;
                            mTextViewActionLabel.setText(mActionList.get(mActionSelected).mActionText);
                            mTextViewActionLabel.animate().alpha(1F).start();
                        }
                        mActionTriggered = true;
                        break;
                    }
                }
                if (!mActionTriggered) {
                    mActionSelected = -1;
                    mTextViewActionLabel.animate().cancel();
                    mTextViewActionLabel.setAlpha(0);
                }
                break;
        }
        return true;
    }

    public void clearActions() {
        mActionList.clear();
        mActionIconList.clear();
        mLinearLayoutActions.removeAllViews();
    }

    void addAction(final Action action) {
        mActionList.add(action);
        View actionView = LayoutInflater.from(getContext())
                .inflate(R.layout.rich_action_view_layout_action, mLinearLayoutActions, false);
        ImageView icon = (ImageView) actionView.findViewById(R.id.rich_action_image_action);
        mActionIconList.add(icon);
        icon.setImageDrawable(getResources().getDrawable(action.mIconResId));
        mLinearLayoutActions.addView(actionView);
    }

}
