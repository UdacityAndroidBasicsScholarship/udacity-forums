package com.example.android.udacityforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signUpHome extends AppCompatActivity {

    EditText name;
    EditText emailId;
    EditText password_sign_up;
    EditText passwordConfirm;
    Button complete_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_home);

        name = findViewById(R.id.Fullname_input);
        emailId = findViewById(R.id.emailid_input);
        password_sign_up = findViewById(R.id.password_sign_up_input);
        passwordConfirm = findViewById(R.id.passwordconfirm_input);
        complete_reg = findViewById(R.id.complete_reg_btn);

        complete_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateEmail();
                validatePassword();
                validateConfirmPassword();
                if (validateEmail() && validatePassword() && validateConfirmPassword()){
                    Intent k = new Intent(signUpHome.this, login_screen.class);
                    startActivity(k);
                }
            }
        });




    }


    // validate method for validation of user data.
    private boolean validateEmail(){
        if(!emailId.getText().toString().contains("@")){
            emailId.setError("Invalid EmailId");
            return false;
        }else{
            return true;
        }
    }

    //validates password.
    private boolean validatePassword(){
        String password2 = password_sign_up.getText().toString();
        if(password2.length() < 8 ){
            password_sign_up.setError("Password should contain at least 8 characters");
            return false;
        }else{
            return true;
        }
    }

    //validates confirm password.
    private boolean validateConfirmPassword(){
        String p = password_sign_up.getText().toString();
        String pc = passwordConfirm.getText().toString();
        if(!p.equals(pc)){
            passwordConfirm.setError("Your password isn't matching");
            return false;
        }else{
            return true;
        }
    }

}
