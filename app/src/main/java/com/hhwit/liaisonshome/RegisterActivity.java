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
    private View mEmailLine;

    private EditText mPasswordText;
    private MyButtonView mPasswordButton;
    private View mPasswordLine;

    private EditText mConfirmText;
    private MyButtonView mConfirmButton;
    private View mConfirmLine;

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

    }

    private void initActions() {
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mEmailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    disableLine(mEmailLine);
                } else {
                    enableLine(mEmailLine);
                }
            }
        });

        mEmailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mEmailButton.setEnable();
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

    }

    @SuppressLint("ResourceAsColor")
    private void disableLine(View v) {
        v.setBackgroundColor(R.color.colorLiaisonGray);
        v.invalidate();
    }

    @SuppressLint("ResourceAsColor")
    private void enableLine(View v) {
        v.setBackgroundColor(R.color.colorPrimaryDark);
        v.invalidate();
    }

}
