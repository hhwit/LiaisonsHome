package com.hhwit.liaisonshome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {
    private MyButtonView mCloseButton;

    private EditText mEmailText;
    private MyButtonView mEmailButton;
    private MyButtonView mEmailLine;

    private EditText mPasswordText;
    private MyButtonView mPasswordButton;
    private MyButtonView mPasswordLine;

    private EditText mConfirmText;
    private MyButtonView mConfirmButton;
    private MyButtonView mConfirmLine;

    private MyButtonView mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        mConfirmText = findViewById(R.id.register_password2_edit);
        mConfirmButton = findViewById(R.id.register_password2_button);
        mConfirmLine = findViewById(R.id.register_password2_line);

        mSignUpButton = findViewById(R.id.register_sign_up_button);

        mEmailButton.setDisable();
        mPasswordButton.setDisable();
        mConfirmButton.setDisable();

        mPasswordLine.setDisable();
        mConfirmLine.setDisable();

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

        // Confirm Text
        mConfirmText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    enableLine(mConfirmLine);
                } else {
                    disableLine(mConfirmLine);
                }
            }
        });

        mConfirmText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mConfirmText.getText().toString().isEmpty()) {
                    mConfirmButton.setDisable();
                } else {
                    mConfirmButton.setEnable();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConfirmText.setText("");
                mConfirmButton.setDisable();
            }
        });

        // Sign up button
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
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
