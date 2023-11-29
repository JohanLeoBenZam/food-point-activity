package com.example.foodpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Perfil extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }



    public void toLogin(View view){
        Intent intent = new Intent(Perfil.this, Login.class);
        startActivity(intent);
    }

    public void toEdit(View view){
        Intent intent = new Intent(Perfil.this,Edit.class);
       /// intent.putExtra("id",getIntent().getIntExtra("id",-1));
        startActivity(intent);
    }
}