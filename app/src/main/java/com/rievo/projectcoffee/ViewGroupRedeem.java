package com.rievo.projectcoffee;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by kwang on 2017-11-08.
 */

public class ViewGroupRedeem extends LinearLayout {

    @BindView(R.id.redeem_listview)
    ListView listView;
    @BindView(R.id.qr_image)
    ImageView qrImage;

    public ViewGroupRedeem(Context context) {
        super(context);
    }

    public ViewGroupRedeem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupRedeem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();


    }

}

