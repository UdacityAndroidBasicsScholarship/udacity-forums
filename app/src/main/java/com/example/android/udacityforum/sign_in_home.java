package com.example.android.udacityforum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class sign_in_home extends AppCompatActivity {

    Button home_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_home);

        home_screen = (Button) findViewById(R.id.home_screen_btn);

        //goes to forum activity on home screen button click.
        home_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(sign_in_home.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
