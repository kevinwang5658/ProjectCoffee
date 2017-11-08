package com.rievo.projectcoffee;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        List items = new ArrayList<String>();

        items.add("Coffee");
        items.add("Lighthouse Mug");
        items.add("Any Menu Item");

        ButterKnife.bind(this);
        listView.setAdapter(new RedeemAdapter(getContext(), R.layout.redeem_item, items));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        qrImage.setImageDrawable(getResources().getDrawable(R.drawable.qr_coffee));
                        break;
                    case 1:
                        qrImage.setImageDrawable(getResources().getDrawable(R.drawable.qr_mug));
                        break;
                    case 2:
                        qrImage.setImageDrawable(getResources().getDrawable(R.drawable.qr_menu));
                        break;
                }
            }
        });

    }

}

