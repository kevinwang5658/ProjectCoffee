package com.rievo.projectcoffee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.rievo.library.BackStack;
import com.rievo.library.BackStackManager;
import com.rievo.library.LinearBackStack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BackStack.install(this);

        BackStackManager backStackManager = BackStack.getBackStackManager();

        FrameLayout root = findViewById(R.id.root);

        LinearBackStack linearBackStack = backStackManager.createLinearBackStack("TAG", root, (layoutInflater, container) -> {
            //This is our first view group in the stack
            ViewGroupLogin vg = (ViewGroupLogin) layoutInflater.inflate(R.layout.viewgroup_login, container, false);

            //Make sure that the view is added to container by the end of this block
            container.addView(vg);

            //Return the view group that was newly inflated
            return vg;
        });

    }

    @Override
    public void onBackPressed() {
        if (!BackStack.getBackStackManager().goBack()) {
            super.onBackPressed();
        }
    }
}
