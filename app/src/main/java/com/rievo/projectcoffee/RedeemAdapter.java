package com.rievo.projectcoffee;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwang on 2017-11-08.
 */

public class RedeemAdapter extends RecyclerView.Adapter<RedeemAdapter.ViewHolder> {

    List<String> list = new ArrayList<>();

    {
        list.add("Coffee");
        list.add("Mug");
        list.add("Menu Item");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.redeem_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.redeem_text);
        }
    }
}
