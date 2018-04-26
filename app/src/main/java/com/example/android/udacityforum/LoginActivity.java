package com.example.android.udacityforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static int APP_REQUEST_CODE = 99;
    EditText emailId;
    EditText password;
    Button sign_in;
    Button sign_up;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            launchSignInActivity();
        }
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
                launchSignInActivity();
                break;

            case R.id.sign_up_btn:
                //goes to the registration screen.
                Intent j = new Intent(LoginActivity.this, SignUpHomeActivity.class);
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

    private void launchSignInActivity() {
        Intent intent = new Intent(this, SignInHomeActivity.class);
        startActivity(intent);
        finish();
    }

    //facebook and account_kit login methods
    public void accountKitLogin(View v) {
        Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) {
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
            } else if (loginResult.getAccessToken() != null) {
                launchSignInActivity();
            }
        }
    }
}