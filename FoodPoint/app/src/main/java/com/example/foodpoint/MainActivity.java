package com.example.foodpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView desayuno = findViewById(R.id.desayuno);
        ImageView americano = findViewById(R.id.americana);
        ImageView espanola = findViewById(R.id.espanola);
        ImageView italiana = findViewById(R.id.italiana);
        ImageView arabe = findViewById(R.id.arabe);
        ImageView japonesa = findViewById(R.id.japonesa);
        ImageView china = findViewById(R.id.china);
        ImageView otros = findViewById(R.id.otros);
        ImageView latina = findViewById(R.id.latina);
        desayuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRestaurantes(v,"desayuno");
            }
        });
        americano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRestaurantes(v,"americana");
            }
        });
        espanola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRestaurantes(v,"espanola");
            }
        });
        arabe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRestaurantes(v,"arabe");
            }
        });
        italiana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRestaurantes(v,"italiana");
            }
        });
        japonesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRestaurantes(v,"japonesa");
            }
        });
        china.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRestaurantes(v,"china");
            }
        });
        otros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRestaurantes(v,"otros");
            }
        });
        latina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRestaurantes(v,"latina");
            }
        });
    }

    public void mostrarRestaurantes(View v, String id){
        Intent intent = new Intent(MainActivity.this,Restaurantes.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}