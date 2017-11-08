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

    private OnClick onClick;

    RedeemAdapter(OnClick listener){
        this.onClick = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.redeem_item, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
        holder.position = position;
    }

    private void onClick(int position){
        onClick.onClick(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        int position;

        public ViewHolder(View itemView, RedeemAdapter adapter) {
            super(itemView);
            textView = itemView.findViewById(R.id.redeem_text);
            itemView.setOnClickListener(v->{
                adapter.onClick(position);
            });
        }
    }

    interface OnClick{
        void onClick(int position);
    }
}
