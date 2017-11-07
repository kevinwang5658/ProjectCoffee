package com.rievo.projectcoffee;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kwang on 2017-11-06.
 */

public class ViewGroupMain extends RelativeLayout {

    @BindView(R.id.points) TextView points;
    //@BindView(R.id.tier_layout) RelativeLayout tierLayout;

    public ViewGroupMain(Context context) {
        super(context);
        init();
    }

    public ViewGroupMain(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewGroupMain(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.navigation_button) public void onNavigationClick(){
        ((MainActivity) getContext()).drawerLayout.openDrawer(Gravity.START);
    }

    @OnClick(R.id.pointsButton) public void onInvisibleButtonClick() {
        points.setText("500");
        //tierLayout.setBackgroundColor(getResources().getColor(R.color.gold));
        ((MainActivity) getContext()).upgrade();
        //tierTitle.setText(getResources().getString(R.string.gold));
    }

    @OnClick(R.id.view) public void onPointViewClick() {
        Log.d("79", "onPointViewClick: ");
        ((MainActivity) getContext()).history();
    }
}
