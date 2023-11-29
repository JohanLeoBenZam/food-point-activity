package com.example.foodpoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private FirebaseFirestore fb = FirebaseFirestore.getInstance();
    private EditText nameText;
    private EditText passText;
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameText = findViewById(R.id.usuarioBox);
        passText = findViewById(R.id.contraseniaBox);
    }

    public void toRegistreActivity(View view){
        Intent intent = new Intent(Login.this,Registre.class);
        startActivity(intent);
    }

    public void toMainActivity(View view){
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    public void comprobar(View view){
        String name = nameText.getText().toString();
        String password = passText.getText().toString();

        fb.document("users/"+name).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            if(documentSnapshot.getString(KEY_PASSWORD).equals(password)){
                                Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                                toMainActivity(view);
                            }
                        }else{
                            Toast.makeText(Login.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}