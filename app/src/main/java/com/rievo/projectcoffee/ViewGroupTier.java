package com.rievo.projectcoffee;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robohorse.pagerbullet.PagerBullet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kwang on 2017-11-07.
 */

public class ViewGroupTier extends LinearLayout {

    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.pager_title)
    TextView viewPagerTitle;

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
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        viewPagerTitle.setText(getResources().getString(R.string.bronze));
                        break;
                    case 1:
                        viewPagerTitle.setText(getResources().getString(R.string.silver));
                        break;
                    case 2:
                        viewPagerTitle.setText(getResources().getString(R.string.gold));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
