package com.rievo.projectcoffee;

import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.rievo.library.BackStack;
import com.rievo.library.BackStackManager;
import com.rievo.library.LinearBackStack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.root) FrameLayout root;
    @BindView(R.id.drawer) DrawerLayout drawerLayout;

    public static final String SP_TAG = "SP_TAG";
    public static final String SP_LOGGED_IN = "SP_LOGGED_IN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BackStack.install(this);

        BackStackManager backStackManager = BackStack.getBackStackManager();

        SharedPreferences sp = getSharedPreferences(SP_TAG, MODE_PRIVATE);
        if (!sp.getBoolean(SP_LOGGED_IN, false)){
            LayoutInflater.from(this).inflate(R.layout.viewgroup_login, root, true);
        } else {
            backStackManager.createLinearBackStack("TAG", root, (layoutInflater, container) -> {
                //This is our first view group in the stack
                ViewGroup vg = (ViewGroup) layoutInflater.inflate(R.layout.main_vg, container, false);

                //Make sure that the view is added to container by the end of this block
                container.addView(vg);

                //Return the view group that was newly inflated
                return vg;
            });
        }

    }

    @Override
    public void onBackPressed() {
        if (!BackStack.getBackStackManager().goBack()) {
            super.onBackPressed();
        }
    }

    protected void login(ViewGroupLogin viewGroupLogin) {
        BackStack.getBackStackManager().createLinearBackStack("TAG", root, (layoutInflater, container) -> {
            //This is our first view group in the stack
            ViewGroup vg = (ViewGroup) layoutInflater.inflate(R.layout.main_vg, container, false);

            //Make sure that the view is added to container by the end of this block
            container.addView(vg);

            //Return the view group that was newly inflated
            return vg;
        });

        root.removeView(viewGroupLogin);
    }
}
