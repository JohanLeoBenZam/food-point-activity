package com.example.foodpoint.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseAux extends SQLiteOpenHelper {

    public static final String DB_NAME = "FoodPoint";

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

        db.execSQL("CREATE TABLE comidas " +
            "(idComida INTEGER PRIMARY KEY AUTOINCREMENT," +
            "tipo VARCHAR(25) NOT NULL," +
            "restaurante VARCHAR(25) NOT NULL," +
            "reserva VARCHAR(25) NOT NULL)");

        db.execSQL("CREATE TABLE tipo " +
            "(idTipo INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreTipo VARCHAR(25) NOT NULL)");

        db.execSQL("CREATE TABLE restaurante " +
            "(idRestaurante INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreRestaurante VARCHAR(25) NOT NULL," +
            "valoracion INTEGER NOT NULL," +
            "direccion VARCHAR(50) NOT NULL," +
            "horario VARCHAR(50) NOT NULL," +
            "telefono VARCHAR(25) NOT NULL)");

        db.execSQL("CREATE TABLE reserva " +
            "(idReserva INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreReserva VARCHAR(25) NOT NULL," +
            "personas INTEGER NOT NULL," +
            "hora VARCHAR(25) NOT NULL," +
            "notaParaRestaurante VARCHAR(200) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
