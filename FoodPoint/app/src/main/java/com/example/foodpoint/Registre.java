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

import java.util.HashMap;
import java.util.Map;

public class Registre extends AppCompatActivity {
    private FirebaseFirestore fb = FirebaseFirestore.getInstance();
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";
    private EditText nameText;
    private EditText passText;
    private EditText confText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);
        passText = findViewById(R.id.contraseniaText);
        confText = findViewById(R.id.confirmarText);
        nameText = findViewById(R.id.usuarioText);
    }

    public void toLogin(View view) {
        Intent intent = new Intent(Registre.this, Login.class);
        startActivity(intent);
    }

    public void registrar(View view) {
        String password = passText.getText().toString();
        String conf = confText.getText().toString();
        String name = nameText.getText().toString();
        Map<String, Object> user = new HashMap<>();
        user.put(KEY_NAME, name);
        user.put(KEY_PASSWORD, password);

        if (conf.equals(password)) {
            fb.document("users/"+name).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Toast.makeText(Registre.this, "Nombre ya usado", Toast.LENGTH_SHORT).show();
                            } else {
                                fb.document("users/"+name).set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(Registre.this, "Usuario agregado", Toast.LENGTH_SHORT).show();
                                                toLogin(view);
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Registre.this, "Error al insertar", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        } else {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }

    }
}