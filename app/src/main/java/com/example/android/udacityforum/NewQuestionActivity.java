package com.example.android.udacityforum;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class NewQuestionActivity extends AppCompatActivity {
    EditText quesTitle;
    EditText quesBody;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);
        quesTitle=findViewById(R.id.title);
        quesBody=findViewById(R.id.body);
    }
    public void submitQuestion(View v){
        String title=quesTitle.getText().toString();
        String body=quesBody.getText().toString();
        Intent intent = new Intent(this, ForumActivity.class);
        startActivity(intent);
    }
}