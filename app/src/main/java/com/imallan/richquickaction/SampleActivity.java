package com.imallan.richquickaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.imallan.quickaction.Action;
import com.imallan.quickaction.RichActionHolder;
import com.imallan.quickaction.RichActionView;

import java.util.ArrayList;
import java.util.List;

public class SampleActivity extends AppCompatActivity {

    private static final String TAG = "SampleActivity";

    private View mButton;

    private View mButton2;

    private RichActionView mActionView;

    public static final String URL = "https://41.media.tumblr.com/d792402242024bf8f490b27233cf5120/tumblr_nzdsseD8kt1smjk7ro6_540.jpg";

    private static final String URL2 = "https://36.media.tumblr.com/c74dfa255e6d749e7bcd5c68a4c9d3e9/tumblr_nzdl7p28Gj1qbe766o6_540.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mActionView = (RichActionView) findViewById(R.id.view_rich_action);
        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
        final ImageView imageView = new ImageView(this);
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(new Action(this, "Action1", android.R.drawable.sym_def_app_icon, 1));
        actions.add(new Action(this, "Action2", android.R.drawable.sym_def_app_icon, 2));
        actions.add(new Action(this, "Action3", android.R.drawable.sym_def_app_icon, 3));
        ArrayList<Action> actions2 = new ArrayList<>();
        actions2.add(new Action(this, "ActionA", android.R.drawable.sym_def_app_icon, 1));
        actions2.add(new Action(this, "ActionB", android.R.drawable.sym_def_app_icon, 2));
        actions2.add(new Action(this, "ActionC", android.R.drawable.sym_def_app_icon, 3));
        MyActionHolder holder1 = new MyActionHolder(actions, URL, imageView);
        MyActionHolder holder2 = new MyActionHolder(actions2, URL2, imageView);
        holder1.bindRichActionView(mButton, mActionView);
        holder2.bindRichActionView(mButton2, mActionView);

//        mActionView.addAction(new Action(this, "Action1", android.R.drawable.sym_def_app_icon, null));
//        mActionView.addAction(new Action(this, "Action2", android.R.drawable.sym_def_app_icon, null));
//        mActionView.addAction(new Action(this, "Action3", android.R.drawable.sym_def_app_icon, null));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mActionView.isViewShown()) {
            mActionView.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    class MyActionHolder extends RichActionHolder {

        final String mUrl;

        final ImageView mImageView;

        private final List<Action> mActions;

        public MyActionHolder(List<Action> actions, String url, ImageView imageView) {
            mActions = actions;
            mUrl = url;
            mImageView = imageView;
        }

        public void bindRichActionView(View view, RichActionView richActionView) {
            view.setTag(mUrl);
            this.bindRichActionView(view, richActionView, mActions);
        }

        @Override
        public void bindRichActionView(View view, RichActionView richActionView, List<Action> actions) {
            super.bindRichActionView(view, richActionView, mActions);
        }

        @Override
        public void onViewPrepare(FrameLayout container, final Object tag, List<Action> actions) {
            super.onViewPrepare(container, tag, actions);
            container.removeAllViews();
            container.addView(mImageView,
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics())
            );
            mImageView.post(
                    new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(mImageView.getContext()).load((String) tag).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .dontAnimate()
                                    .into(mImageView);
                        }
                    }
            );
        }

        @Override
        public void onViewShown(FrameLayout container) {

        }

        @Override
        public void onActionSelected(Action action, Object tag) {
            switch (action.getActionId()) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    }

}
