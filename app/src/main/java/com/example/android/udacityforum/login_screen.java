package com.example.android.udacityforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login_screen extends AppCompatActivity {

    EditText EmailId;
    EditText password;
    Button sign_in;
    Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        EmailId = (EditText)findViewById(R.id.username_input);
        password =(EditText)findViewById(R.id.password_input);
        sign_in = (Button)findViewById(R.id.sign_in_btn);
        sign_up = (Button)findViewById(R.id.sign_up_btn);



        // goes to sign in home screen.
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checks for errors and displays them before clicking.
                validateEmail();
                validatePassword();
                // if conditions of validate method is met.
                if (validateEmail() && validatePassword()){
                    Intent i = new Intent(login_screen.this, sign_in_home.class);
                    startActivity(i);
                }

            }
        });

        //goes to the registration screen.
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent (login_screen.this,signUpHome.class);
                startActivity(j);
            }
        });

    }

    //validates email id.
    private boolean validateEmail(){
        if(!EmailId.getText().toString().contains("@")){
            EmailId.setError("Invalid Email-Id");
            return false;
        }else{
            return true;
        }
    }

    //validates password.
    private boolean validatePassword(){
        String password2 = password.getText().toString();
        if(password2.length() < 8 ){
            password.setError("Password should contain at least 8 characters");
            return false;
        }else{
            return true;
        }
    }


}
