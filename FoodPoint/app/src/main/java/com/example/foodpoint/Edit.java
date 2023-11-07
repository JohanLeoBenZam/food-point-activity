package com.example.foodpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.foodpoint.database.DatabaseAux;

public class Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        EditText userText = findViewById(R.id.usuarioedit);
        EditText contraseniaText = findViewById(R.id.contraseniaedit);

        SQLiteDatabase db = new DatabaseAux(this).getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users where userid="+getIntent().getIntExtra("id",-1),null);
        cursor.moveToFirst();
        userText.setText(cursor.getString(1));
        contraseniaText.setText(cursor.getString(2));
    }

    public void editar(View view){
        EditText userText = findViewById(R.id.usuarioedit);
        EditText contraseniaText = findViewById(R.id.contraseniaedit);

        SQLiteDatabase db = new DatabaseAux(this).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username",userText.getText().toString());
        values.put("userpassword",contraseniaText.getText().toString());

        db.update("users",values,"userid="+getIntent().getIntExtra("id",-1),null);

        Intent intent = new Intent(Edit.this,Perfil.class);
        intent.putExtra("id",getIntent().getIntExtra("id",-1));
        startActivity(intent);
    }
}