package com.rievo.projectcoffee;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewGroupHistory extends RelativeLayout {

    @BindView(R.id.history) RecyclerView history;
    @BindView(R.id.history_layout) FrameLayout historyLayout;

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
        YoYo.with(Techniques.FadeIn)
                .duration(100)
                .playOn(historyLayout);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        history.setLayoutManager(manager);
        history.setHasFixedSize(true);

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");


        List<String> content = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            c.add(Calendar.DAY_OF_YEAR, -1);
            String formattedDate = df.format(c.getTime());
            content.add(formattedDate);
        }

        ParallaxRecyclerAdapter<String> stringAdapter = new ParallaxRecyclerAdapter<String>(content) {
            @Override
            public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter, int i) {
                ((SimpleViewHolder) viewHolder).setDate(viewHolder.itemView, content.get(i));
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter, int i) {
                return new SimpleViewHolder(((MainActivity)getContext()).getLayoutInflater().inflate(R.layout.cell, viewGroup, false));
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

            TextView date = (TextView) itemView.findViewById(R.id.date);
            TextView amount = (TextView) itemView.findViewById(R.id.amount);
            TextView points = (TextView) itemView.findViewById(R.id.points_earned);

            date.setText("Nov 1, 2017");
            Random random = new Random();
            int amountNum = random.nextInt(15) + 1;
            int pointsNum = amountNum * 10;

            amount.setText("$" + Integer.toString(amountNum) + ".00");
            points.setText(Integer.toString(pointsNum) + " points earned");

        }

        public void setDate(View itemView, String dateString){
            TextView date = (TextView) itemView.findViewById(R.id.date);

            date.setText(dateString);

        }
    }
}
