package com.example.foodpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Restaurantes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantes);

        FirebaseFirestore fb = FirebaseFirestore.getInstance();
        fb.collection("restaurantes").whereArrayContains("categorias",getIntent().getStringExtra("id").toString())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot d : queryDocumentSnapshots){
                            String nombreImagen = d.getString("img");
                            int resID = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());

                                ImageView imagen = new ImageView(Restaurantes.this);
                                imagen.setImageResource(resID);
                                TextView text = new TextView(Restaurantes.this);
                                text.setText("Restaurante "+d.getString("name")+"\n");
                                text.setGravity(Gravity.CENTER);
                                text.setTypeface(null,Typeface.BOLD);
                                text.setTextColor(getColor(R.color.black));
                                LinearLayout layout = findViewById(R.id.restaurantesL);
                                layout.addView(imagen);
                                layout.addView(text);

                        }
                    }
                });

    }


}