package com.example.android.udacityforum;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class ForumActivity extends AppCompatActivity {
    RecyclerView rv;
    FloatingActionButton fab_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        rv=findViewById(R.id.recycler_view);
        fab_button=findViewById(R.id.fab_button);
        ArrayList<QuestionFormat> quesList=new ArrayList<>();
        //sample data---------------------------------------
        for(int i=0;i<15;i++){
            quesList.add(new QuestionFormat("heading "+i ,"content "+i));
        }
        //-----------------------------------------------
        MyForumRecyclerViewAdapter adapter=new MyForumRecyclerViewAdapter(quesList,this);
        rv.setAdapter(adapter);
        fab_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NewQuestionActivity.class));
            }
        });


    }
}
