package com.rievo.projectcoffee;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwang on 2017-11-08.
 */

public class RedeemAdapter extends ArrayAdapter<String> {

    public RedeemAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public RedeemAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.redeem_item, null);
        }

        String string = getItem(position);

        if (string != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.redeem_text);
            TextView tt2 = v.findViewById(R.id.redeem_points);

            if (tt1 != null) {
                tt1.setText(string);
            }

            if (tt2 != null) {
                if (string == "Any Menu Item") {
                    tt2.setText("200 points");
                } else {
                    tt2.setText("0 points");
                }
            }
        }

        return v;
    }

}
