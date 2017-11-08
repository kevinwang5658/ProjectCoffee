package com.rievo.projectcoffee;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rievo.library.BackStack;
import com.rievo.library.LinearBackStack;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.rievo.projectcoffee.MainActivity.SP_TAG;

/**
 * Created by kwang on 2017-11-06.
 */

public class ViewGroupMain extends RelativeLayout {

    @BindView(R.id.points) TextView points;
    @BindView(R.id.drawer_top) RelativeLayout drawerTop;
    @BindView(R.id.drawer_text) TextView drawerText;
    @BindView(R.id.view) View view;
    @BindView(R.id.viewgroup_tier) ViewGroupTier viewGroupTier;
    @BindView(R.id.multiplier) TextView multiplier;
    @BindView(R.id.redeem_button) Button redeemButton;

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
        if (points.getText() == getResources().getString(R.string.starting_points)) {
            points.setText(getResources().getString(R.string.silver_points));
            drawerTop.setBackgroundColor(getResources().getColor(R.color.silver));
            drawerText.setText(getResources().getString(R.string.silver));
            ((MainActivity) getContext()).upgrade(R.layout.dialog_upgrade_silver);
            viewGroupTier.setViewPagerPage(1);
            viewGroupTier.setArcSilver();
            multiplier.setText(getResources().getString(R.string.silver_multiplier));
        } else {
            points.setText(getResources().getString(R.string.gold_points));
            drawerTop.setBackgroundColor(getResources().getColor(R.color.gold));
            drawerText.setText(getResources().getString(R.string.gold));
            ((MainActivity) getContext()).upgrade(R.layout.dialog_upgrade_gold);
            viewGroupTier.setViewPagerPage(2);
            viewGroupTier.setArcGold();
            multiplier.setText(getResources().getString(R.string.gold_multiplier));
        }
    }

    @OnClick(R.id.view) public void onPointViewClick() {
        /*YoYo.with(Techniques.FadeOut)
                .duration(50)
                .onEnd(callback->{*/
                    //view.setVisibility(GONE);
                    view.animate().scaleXBy(35).scaleYBy(35).setDuration(400)
                            .withEndAction(()->{
                                view.setVisibility(VISIBLE);
                                ((MainActivity) getContext()).history();
                            })
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {

                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .start();
                //})
                findViewById(R.id.text_layout).setVisibility(GONE);

    }

    @OnClick(R.id.redeem_button) public void onClick(){
        ((LinearBackStack) BackStack.getStack(SP_TAG)).add((layoutInflater, container) -> {
            ViewGroup view = (ViewGroup) layoutInflater.inflate(R.layout.redeem_vg, container, false);
            container.addView(view);
            return view;
        });
    }
}
