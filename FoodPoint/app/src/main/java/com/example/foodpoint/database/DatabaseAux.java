package com.example.foodpoint.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseAux extends SQLiteOpenHelper {

    public static final String DB_NAME = "Food point";

    public static final int DB_VERSION = 1 ;

    public DatabaseAux(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (" +
                        "userid INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "username VARCHAR(25) NOT NULL," +
                        "userpassword VARCHAR(25) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
