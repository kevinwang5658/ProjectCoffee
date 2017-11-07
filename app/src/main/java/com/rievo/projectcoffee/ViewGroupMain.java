package com.rievo.projectcoffee;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
        drawerTop.setBackgroundColor(getResources().getColor(R.color.silver));
        drawerText.setText(getResources().getString(R.string.silver));
        ((MainActivity) getContext()).upgrade();
    }

    @OnClick(R.id.view) public void onPointViewClick() {
        ViewGroup.LayoutParams layoutParams1 = view.getLayoutParams();
        /*LayoutParams layoutParams = new LayoutParams(3000, 3000);
        layoutParams.setMargins(-600, -600, -600, -600);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        view.setLayoutParams(layoutParams);
        view.invalidate();
        view.setElevation(100);*/



        YoYo.with(Techniques.FadeOut)
                .duration(100)
                .onEnd(callback->{
                    ((ViewGroup) view).removeView(view.findViewById(R.id.text_layout));
                    view.animate().scaleXBy(20).scaleYBy(20).setDuration(400)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    ((MainActivity) getContext()).history();
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .start();
                })
                .playOn(findViewById(R.id.text_layout));
    }
}
