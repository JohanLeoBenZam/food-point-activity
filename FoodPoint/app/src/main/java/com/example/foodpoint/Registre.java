package com.example.foodpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodpoint.database.DatabaseAux;

public class Registre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
    }

    public void toMainActivity(View view){
        Intent intent = new Intent(Registre.this,MainActivity.class);
        startActivity(intent);
    }

    public void registrar(View view){
        EditText contraseniaText = findViewById(R.id.contraseniaText);
        EditText confirmar = findViewById(R.id.confirmarText);

        String contraseniaString = contraseniaText.getText().toString();
        String confirmarString = confirmar.getText().toString();

        if(!contraseniaString.isEmpty()&&contraseniaString.equals(confirmarString)){
            EditText usuarioText = findViewById(R.id.usuarioText);
            String usuarioString = usuarioText.getText().toString();

            if(!usuarioString.trim().isEmpty()){
                SQLiteDatabase dbReadable = new DatabaseAux(this).getReadableDatabase();
                Cursor cursor = dbReadable.rawQuery("SELECT * FROM users",null);

                boolean repetido=false;

                    if(cursor.moveToFirst()){
                        do{
                        String usuario = cursor.getString(1);
                        if(usuario.equals(usuarioString)){
                            repetido=true;
                            break;
                        }
                    }while(cursor.moveToNext());
                    }

                if(!repetido){
                    SQLiteDatabase dbWridable = new DatabaseAux(this).getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("username",usuarioString);
                    values.put("userpassword",contraseniaString);

                    long res = dbWridable.insert("users",null,values);
                    if(res<0){
                        Toast.makeText(this, "Error en la base de datos.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "Usuario agregado correctamente.", Toast.LENGTH_SHORT).show();
                        toMainActivity(view);
                    }

                }else{
                    Toast.makeText(this, "Nombre de usuario ya usado.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Usuario vacio.", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this, "Las contraseña no coinciden.", Toast.LENGTH_SHORT).show();
        }
    }
}