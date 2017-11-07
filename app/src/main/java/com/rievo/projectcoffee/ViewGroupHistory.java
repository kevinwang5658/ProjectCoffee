package com.rievo.projectcoffee;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewGroupHistory extends RelativeLayout {

    @BindView(R.id.history) RecyclerView history;

    public ViewGroupHistory(Context context) {
        super(context);
    }

    public ViewGroupHistory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupHistory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        history.setLayoutManager(manager);
        history.setHasFixedSize(true);


        final List<String> content = new ArrayList<>();
        for (int i = 0; i < 30; i++)
            content.add("dsaf");

        ParallaxRecyclerAdapter<String> stringAdapter = new ParallaxRecyclerAdapter<String>(content) {
            @Override
            public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter, int i) {
                ((TextView) viewHolder.itemView).setText(content.get(i));
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter, int i) {
                return new SimpleViewHolder(((MainActivity)getContext()).getLayoutInflater().inflate(android.R.layout.simple_list_item_1, viewGroup, false));
            }

            @Override
            public int getItemCountImpl(ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter) {
                return content.size();
            }
        };

        stringAdapter.setParallaxHeader(((MainActivity)getContext()).getLayoutInflater().inflate(R.layout.header_history, history, false), history);
        stringAdapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                //TODO: implement toolbar alpha. See README for details
            }
        });
        history.setAdapter(stringAdapter);
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {

        public SimpleViewHolder(View itemView) {
            super(itemView);
        }


    public String getListString(int position) {
        return position + " - android";
    }

    }
}
