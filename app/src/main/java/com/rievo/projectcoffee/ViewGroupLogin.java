package com.rievo.projectcoffee;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class ViewGroupLogin extends RelativeLayout {

    private Button buttonLogin;
    private Button buttonSignUp;
    private ImageButton buttonBack;
    private EditText editTextUsername;
    private EditText editTextPassword;

    private int loginState = 0;


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
        buttonBack = findViewById(R.id.buttonBack);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginState == 0) {
                    editTextUsername.setVisibility(VISIBLE);
                    editTextPassword.setVisibility(VISIBLE);
                    YoYo.with(Techniques.FadeIn).duration(400).playOn(editTextUsername);
                    YoYo.with(Techniques.FadeIn).duration(400).playOn(editTextPassword);
                    YoYo.with(Techniques.FadeOut).duration(400).playOn(buttonSignUp);
                    buttonBack.setVisibility(VISIBLE);
                    buttonLogin.animate().translationY(300);
                    loginState = 1;
                } else {
                    ((MainActivity) getContext()).login(ViewGroupLogin.this);
                }
            }
        });

        buttonBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginState == 1) {
                    buttonBack.setVisibility(GONE);
                    YoYo.with(Techniques.FadeOut).duration(400).playOn(editTextUsername);
                    YoYo.with(Techniques.FadeOut).duration(400).playOn(editTextPassword);
                    YoYo.with(Techniques.FadeIn).duration(400).playOn(buttonSignUp);
                    buttonLogin.animate().translationY(0);
                    loginState = 0;
                }
            }
        });
    }

}
