package com.example.android.udacityforum;

import android.content.ContentValues;
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
        db=openOrCreateDatabase("ForumDB",MODE_PRIVATE,null);
        db.execSQL("create table if not exists questionsTable(title varchar,body varchar)");
    }
    public void submitQuestion(View v){
        String title=quesTitle.getText().toString();
        String body=quesBody.getText().toString();
        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("body",body);
        try {
            db.insert("questionsTable", null, values);
        }catch(Exception e){
            Log.d("critical error","error occured in quesTable insert");
        }
//        Cursor cursor=db.rawQuery("select * from questionsTable",null);
//        while(cursor.moveToNext()){
//            String tabledatatitle=cursor.getString(0);
//            String tabledatabody=cursor.getString(1);
//            Log.d(tabledatatitle,tabledatabody);
//        }

    }
}