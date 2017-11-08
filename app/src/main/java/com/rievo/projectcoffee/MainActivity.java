package com.rievo.projectcoffee;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rievo.library.BackStack;
import com.rievo.library.BackStackManager;
import com.rievo.library.LinearBackStack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            LayoutInflater.from(this).inflate(R.layout.viewgroup_login, root, true);
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            createBackStack();
        }
    }

    @Override
    public void onBackPressed() {
        if (!BackStack.getBackStackManager().goBack()) {
            //super.onBackPressed();
        }
    }

    @OnClick(R.id.signOut)
    public void signOut() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        SharedPreferences sp = getSharedPreferences(SP_TAG, MODE_PRIVATE);
        sp.edit().putBoolean(SP_LOGGED_IN, false).apply();
        LayoutInflater.from(this).inflate(R.layout.viewgroup_login, root, true);
    }

    protected void login(ViewGroupLogin viewGroupLogin) {
        SharedPreferences sp = getSharedPreferences(SP_TAG, MODE_PRIVATE);
        sp.edit().putBoolean(SP_LOGGED_IN, true).apply();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        createBackStack();
        root.removeView(viewGroupLogin);
    }

    protected void upgrade(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_upgrade, null))
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .show();
    }

    protected void history() {
        ((LinearBackStack) BackStack.getStack(SP_TAG)).builder((layoutInflater, container) -> {
            //This is our first view group in the stack
            ViewGroup vg = (ViewGroup) layoutInflater.inflate(R.layout.viewgroup_history, container, false);

            //Make sure that the view is added to container by the end of this block
            container.addView(vg);

            Log.d(SP_TAG, "history: has backstack");
            //Return the view group that was newly inflated
            return vg;
        }).removeAnimator((v, a)->{
            v.getRootView().findViewById(R.id.view).animate().scaleX(1).scaleY(1).withEndAction(()->
                    findViewById(R.id.text_layout).setVisibility(View.VISIBLE)).start();
            /*YoYo.with(Techniques.FadeIn)
                    .duration(300)
                    .delay(100000)*/
            a.done();
        }).build();
    }

    private void createBackStack(){
        BackStack.getBackStackManager().builder(SP_TAG)
                .setContainer(root)
                .shouldRetain(true)
                .shouldAllowDuplicates(false)
                .viewCreator((layoutInflater, container) -> {
                    //This is our first view group in the stack
                    ViewGroup vg = (ViewGroup) layoutInflater.inflate(R.layout.main_vg, container, false);

                    //Make sure that the view is added to container by the end of this block
                    container.addView(vg);

                    //Return the view group that was newly inflated
                    return vg;
                }).build();

    }
}
