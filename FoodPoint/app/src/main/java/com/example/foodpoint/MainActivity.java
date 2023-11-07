package com.example.foodpoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodpoint.database.DatabaseAux;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toRegistreActivity(View view){
        Intent intent = new Intent(MainActivity.this,Registre.class);
        startActivity(intent);
    }

    public void toPerfil(View view){

    }

    public void comprobar(View view){
        SQLiteDatabase db = new DatabaseAux(this).getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users",null);

        EditText usernameText = findViewById(R.id.usuarioBox);
        EditText userpassword = findViewById(R.id.contraseniaBox);

        String usernameString = usernameText.getText().toString();
        String passwordString = userpassword.getText().toString();

        if(cursor.moveToFirst()){
            boolean encontrado=false;
            do {
                String username = cursor.getString(1);
                String password = cursor.getString(2);

                if (username.equals(usernameString)) {
                    if (password.equals(passwordString)){
                        encontrado=true;
                        Intent intent = new Intent(MainActivity.this,Perfil.class);
                        intent.putExtra("id",cursor.getInt(0));
                        startActivity(intent);
                    }else{
                        break;
                    }
                }
            }while(cursor.moveToNext());

            if(!encontrado){
                Toast.makeText(this,"Datos incorrectos.",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"Error. No hay datos en la base de datos.",Toast.LENGTH_SHORT).show();
        }
    }


}