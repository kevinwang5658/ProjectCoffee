package com.rievo.projectcoffee;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kwang on 2017-11-07.
 */

public class Adapter extends PagerAdapter {

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = null;
        switch (position){
            case 0: view = inflater.inflate(R.layout.tier_card_silver, container, false);break;
            case 1: view = inflater.inflate(R.layout.tier_card_gold, container, false); break;
        }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
