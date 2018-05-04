package com.example.android.udacityforum.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UdacityForumDatabaseHelper extends SQLiteOpenHelper {

    //DB version and DB name

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "udacity_forum_db";

    public UdacityForumDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //for creating new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0, DATABASE_VERSION);
    }

    //for updating new database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion, newVersion);
    }

    //method for updating or creating database based on db version
    public void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}