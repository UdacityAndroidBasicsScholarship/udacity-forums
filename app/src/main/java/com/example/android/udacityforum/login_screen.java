package com.example.android.udacityforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class login_screen extends AppCompatActivity implements View.OnClickListener {

    EditText emailId;
    EditText password;
    Button sign_in;
    Button sign_up;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        emailId = (EditText) findViewById(R.id.username_input);
        password = (EditText) findViewById(R.id.password_input);
        sign_in = (Button) findViewById(R.id.sign_in_btn);
        sign_up = (Button) findViewById(R.id.sign_up_btn);

        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
        sign_in.setEnabled(false);
        sign_up.setTextColor(getResources().getColor(R.color.colorBlack));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAddTextChangeListener();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.sign_in_btn:
                Intent i = new Intent(login_screen.this, sign_in_home.class);
                startActivity(i);
                break;

            case R.id.sign_up_btn:
                //goes to the registration screen.
                Intent j = new Intent(login_screen.this, signUpHome.class);
                startActivity(j);
                break;
        }
    }

    private void setAddTextChangeListener() {
        emailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                onTextChangedListener(emailId.getText().toString().trim(),
                        password.getText().toString().trim());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                onTextChangedListener(emailId.getText().toString(),
                        password.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }


    public void onTextChangedListener(String userName, String password) {

        if (password!= null && password.length() >= 8) {

            if (userName.matches(emailPattern)) {
                enableBtn();
            } else {
                disableBtn();
            }
        } else {
            disableBtn();
        }
    }


    private void enableBtn() {
        sign_in.setEnabled(true);
        sign_in.setTextColor(getResources().getColor(R.color.colorBlack));
        sign_up.setTextColor(getResources().getColor(R.color.colorBlack));
    }

    private void disableBtn() {
        sign_in.setEnabled(false);
        sign_in.setTextColor(getResources().getColor(R.color.colorWhite));
        sign_up.setTextColor(getResources().getColor(R.color.colorBlack));
    }
}