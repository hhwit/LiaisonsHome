package com.hhwit.liaisonshome;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
    private MyButtonView mCloseButton;

    private EditText mEmailText;
    private MyButtonView mEmailButton;
    private MyButtonView mEmailLine;

    private EditText mPasswordText;
    private MyButtonView mPasswordButton;
    private MyButtonView mPasswordLine;

    private MyButtonView mSignUpButton;
    private TextView mToRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initActions();
    }

    @SuppressLint("ResourceAsColor")
    private void initViews() {
        mCloseButton = findViewById(R.id.register_close_button);

        mEmailText = findViewById(R.id.register_email_edit);
        mEmailButton = findViewById(R.id.register_email_button);
        mEmailLine = findViewById(R.id.register_email_line);

        mPasswordText = findViewById(R.id.register_password1_edit);
        mPasswordButton = findViewById(R.id.register_password1_button);
        mPasswordLine = findViewById(R.id.register_password1_line);

        mSignUpButton = findViewById(R.id.register_sign_up_button);
        mToRegisterButton = findViewById(R.id.login_to_register);

        mEmailButton.setDisable();
        mPasswordButton.setDisable();
        mPasswordLine.setDisable();

    }

    private void initActions() {
        // Close button
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Email text
        mEmailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    enableLine(mEmailLine);
                } else {
                    disableLine(mEmailLine);
                }
            }
        });

        mEmailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mEmailText.getText().toString().isEmpty()) {
                    mEmailButton.setDisable();
                } else {
                    mEmailButton.setEnable();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmailText.setText("");
                mEmailButton.setDisable();
            }
        });

        // Password Text
        mPasswordText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    enableLine(mPasswordLine);
                } else {
                    disableLine(mPasswordLine);
                }
            }
        });

        mPasswordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mPasswordText.getText().toString().isEmpty()) {
                    mPasswordButton.setDisable();
                } else {
                    mPasswordButton.setEnable();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPasswordText.setText("");
                mPasswordButton.setDisable();
            }
        });

        // Sign up button
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
            }
        });

        // To register button
        mToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,
                        RegisterActivity.class));
                finish();
            }
        });

    }

    private void disableLine(View v) {
        ((MyButtonView)v).setDisable();
    }

    private void enableLine(View v) {
        ((MyButtonView)v).setEnable();
    }

}
