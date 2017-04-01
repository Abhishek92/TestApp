package com.android.testapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

/**
 * Created by hp pc on 27-03-2017.
 */

public class CustomSwipeableLayout extends FrameLayout {

    private ViewGroup mFViewGroup;
    private ViewGroup mSViewGroup;
    private Context mContext;
    private boolean isSlideToDown;
    private boolean isSlideToAbove;

    public CustomSwipeableLayout(Context context) {
        super(context);
        mContext = context;
    }

    public CustomSwipeableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CustomSwipeableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    public void setViewGroups(int firstLayout, int secondLayout)
    {
        mFViewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(firstLayout, null);
        mSViewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(secondLayout, null);

        addView(mSViewGroup, 0);
        addView(mFViewGroup, 1);

        this.setOnTouchListener(new OnSwipeTouchListener(mContext) {
            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                slideToAbove();
            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                slideToDown();
            }
        });

    }

    public void slideToDown() {
        if(!isSlideToDown) {
            isSlideToDown = true;
            isSlideToAbove = false;
            Animation slide = new TranslateAnimation(0, 0, -mFViewGroup.getHeight(), 0);
            slide.setInterpolator(new DecelerateInterpolator());
            slide.setDuration(400);
            slide.setFillAfter(true);
            slide.setFillEnabled(true);
            mFViewGroup.startAnimation(slide);
        }
    }

    public void slideToAbove() {
        if(!isSlideToAbove) {
            isSlideToAbove = true;
            isSlideToDown = false;
            Animation slide = new TranslateAnimation(0, 0, 0, -mFViewGroup.getHeight());
            slide.setInterpolator(new AccelerateInterpolator());
            slide.setDuration(400);
            slide.setFillAfter(true);
            slide.setFillEnabled(true);
            mFViewGroup.startAnimation(slide);
        }

    }


}
