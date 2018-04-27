package com.example.android.udacityforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signUpHome extends AppCompatActivity {

    EditText name;
    EditText emailId;
    EditText password;
    EditText confirmPassword;
    Button complete_reg;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    String entered_emailID = null;
    String entered_password = null;
    String entered_confirmPassword = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_home);

        name = findViewById(R.id.Fullname_input);
        emailId = findViewById(R.id.emailid_input);
        password = findViewById(R.id.password_sign_up_input);
        confirmPassword = findViewById(R.id.passwordconfirm_input);
        complete_reg = findViewById(R.id.complete_reg_btn);
//        disableBtn();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAddTextChangeListener();
    }

    private void setAddTextChangeListener() {
        emailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateFields(entered_emailID, entered_password, entered_confirmPassword);
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateFields(entered_emailID, entered_password, entered_confirmPassword);
            }
        });

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                confirmPassword.getText();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateFields(entered_emailID, entered_password, entered_confirmPassword);
            }
        });
    }

    private void init() {

        validateFields(entered_emailID, entered_password, entered_confirmPassword);

        if (validateFields(entered_emailID, entered_password, entered_confirmPassword)) {
            enableBtn();
        } else {
            disableBtn();
        }

        complete_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().equalsIgnoreCase(confirmPassword.getText().toString())) {
                    Intent intent = new Intent(signUpHome.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    emailId.setError("Password mismatch");
                }
            }
        });
    }

    private boolean validateFields(String emailID, String password, String confirmPassword) {

        if (emailID != null && emailID.length() > 0 && emailID.matches(emailPattern)) {
            if ((password != null) && (confirmPassword != null)) {
                return true;
            } else return false;
        } else return false;
    }

    private void enableBtn() {
        complete_reg.setEnabled(true);
        complete_reg.setTextColor(getResources().getColor(R.color.colorBlack));
    }

    private void disableBtn() {
        complete_reg.setEnabled(false);
        complete_reg.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    public void launchLoginScreen(View v) {
        if (validateFields(entered_emailID, entered_password, entered_confirmPassword)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
