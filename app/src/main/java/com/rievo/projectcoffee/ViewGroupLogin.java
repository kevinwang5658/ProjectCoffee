package com.rievo.projectcoffee;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class ViewGroupLogin extends RelativeLayout {

    private Button buttonLogin;
    private Button buttonSignUp;
    private EditText editTextUsername;
    private EditText editTextPassword;


    public ViewGroupLogin(Context context) {
        super(context);
    }

    public ViewGroupLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupLogin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onAttachedToWindow () {
        super.onAttachedToWindow();

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextUsername.getVisibility() == GONE) {
                    editTextUsername.setVisibility(VISIBLE);
                    editTextPassword.setVisibility(VISIBLE);
                    buttonSignUp.setVisibility(GONE);
                    buttonLogin.animate().translationY(300);
                } else {
                    ((MainActivity) getContext()).login();
                }
            }
        });

    }

}
