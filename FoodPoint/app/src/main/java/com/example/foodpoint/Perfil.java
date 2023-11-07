package com.example.foodpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodpoint.database.DatabaseAux;

import org.w3c.dom.Text;

public class Perfil extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        TextView usuarioText = findViewById(R.id.perfilUsername);
        TextView contrasenia = findViewById(R.id.perfilContrasenia);

        SQLiteDatabase db = new DatabaseAux(this).getReadableDatabase();

        Cursor cursor= db.rawQuery("SELECT * FROM users where userid="+getIntent().getIntExtra("id",-1),null);
        cursor.moveToFirst();
        usuarioText.setText(cursor.getString(1));
        contrasenia.setText(cursor.getString(2));
    }

   public void eliminar (View view){
        SQLiteDatabase db = new DatabaseAux(this).getWritableDatabase();
        if(db.delete("users","userid="+getIntent().getIntExtra("id",-1),null)<=0){
            Toast.makeText(this,"No se pudo eliminar",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Cuenta eliminada",Toast.LENGTH_SHORT).show();
            toLogin(view);
        }

   }

    public void toLogin(View view){
        Intent intent = new Intent(Perfil.this,MainActivity.class);
        startActivity(intent);
    }

    public void toEdit(View view){
        Intent intent = new Intent(Perfil.this,Edit.class);
        intent.putExtra("id",getIntent().getIntExtra("id",-1));
        startActivity(intent);
    }
}