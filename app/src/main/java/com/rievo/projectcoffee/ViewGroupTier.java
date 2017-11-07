package com.rievo.projectcoffee;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kwang on 2017-11-07.
 */

public class ViewGroupTier extends LinearLayout {

    @BindView(R.id.view_pager) ViewPager viewPager;

    public ViewGroupTier(Context context) {
        super(context);
    }

    public ViewGroupTier(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupTier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ButterKnife.bind(this);

        viewPager.setAdapter(new Adapter());
    }
}
