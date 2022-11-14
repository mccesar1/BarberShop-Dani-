package com.example.firebasedani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApartarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartar);

        Button btnLunes = findViewById(R.id.btnLunes);
        Button btnMartes = findViewById(R.id.btnMartes);
        Button btnSabado = findViewById(R.id.btnSabado);
        Button btnMiercoles = findViewById(R.id.btnMiercoles);
        Button btnJueves = findViewById(R.id.btnJueves);
        Button btnViernes = findViewById(R.id.btnViernes);
        Button btnBack2 = findViewById(R.id.btnBack2);

        FirebaseDatabase db = FirebaseDatabase.getInstance();

        db.getReference().child("reservaciones").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot document = task.getResult();
                document.getChildren().forEach(dia -> {
                            if (dia.getKey().equals("lunes")) {
                                dia.getChildren().forEach(hora -> {
                                    if (hora.getKey().equals("activo") && hora.getValue().toString().equals("true")) {
                                        btnLunes.setEnabled(true);
                                        btnLunes.setBackgroundColor(Color.parseColor("#71e676"));
                                    } else if (hora.getKey().equals("activo") && hora.getValue().toString().equals("false")) {
                                        btnLunes.setBackgroundColor(Color.parseColor("#A6ada6"));
                                        btnLunes.setEnabled(false);
                                    }
                                });
                            } else if (dia.getKey().equals("martes")) {
                                dia.getChildren().forEach(hora -> {
                                    if (hora.getKey().equals("activo") && hora.getValue().toString().equals("true")) {
                                        btnMartes.setEnabled(true);
                                        btnMartes.setBackgroundColor(Color.parseColor("#71e676"));
                                    } else if (hora.getKey().equals("activo") && hora.getValue().toString().equals("false")) {
                                        btnMartes.setBackgroundColor(Color.parseColor("#A6ada6"));
                                        btnMartes.setEnabled(false);
                                    }
                                });
                            } else if (dia.getKey().equals("miercoles")) {
                                dia.getChildren().forEach(hora -> {
                                    if (hora.getKey().equals("activo") && hora.getValue().toString().equals("true")) {
                                        btnMiercoles.setEnabled(true);
                                        btnMiercoles.setBackgroundColor(Color.parseColor("#71e676"));
                                    } else if (hora.getKey().equals("activo") && hora.getValue().toString().equals("false")) {
                                        btnMiercoles.setBackgroundColor(Color.parseColor("#A6ada6"));
                                        btnMiercoles.setEnabled(false);
                                    }
                                });
                            } else if (dia.getKey().equals("jueves")) {
                                dia.getChildren().forEach(hora -> {
                                    if (hora.getKey().equals("activo") && hora.getValue().toString().equals("true")) {
                                        btnJueves.setEnabled(true);
                                        btnJueves.setBackgroundColor(Color.parseColor("#71e676"));
                                    } else if (hora.getKey().equals("activo") && hora.getValue().toString().equals("false")) {
                                        btnJueves.setBackgroundColor(Color.parseColor("#A6ada6"));
                                        btnJueves.setEnabled(false);
                                    }
                                });
                            } else if (dia.getKey().equals("viernes")) {
                                dia.getChildren().forEach(hora -> {
                                    if (hora.getKey().equals("activo") && hora.getValue().toString().equals("true")) {
                                        btnViernes.setEnabled(true);
                                        btnViernes.setBackgroundColor(Color.parseColor("#71e676"));
                                    } else if (hora.getKey().equals("activo") && hora.getValue().toString().equals("false")) {
                                        btnViernes.setBackgroundColor(Color.parseColor("#A6ada6"));
                                        btnViernes.setEnabled(false);
                                    }
                                });
                            } else if (dia.getKey().equals("sabado")) {
                                dia.getChildren().forEach(hora -> {
                                    if (hora.getKey().equals("activo") && hora.getValue().toString().equals("true")) {
                                        btnSabado.setEnabled(true);
                                        btnSabado.setBackgroundColor(Color.parseColor("#71e676"));
                                    } else if (hora.getKey().equals("activo") && hora.getValue().toString().equals("false")) {
                                        btnSabado.setBackgroundColor(Color.parseColor("#A6ada6"));
                                        btnSabado.setEnabled(false);
                                    }
                                });
                                }});
                            }
                        });
                btnLunes.setOnClickListener(v -> {
                    Intent intent = new Intent(this, HoraActivity.class);
                    intent.putExtra("dia", "lunes");
                    startActivity(intent);
                });
                btnMartes.setOnClickListener(v -> {
                    Intent intent = new Intent(this, HoraActivity.class);
                    intent.putExtra("dia", "martes");
                    startActivity(intent);

                });
                btnMiercoles.setOnClickListener(v -> {
                    Intent intent = new Intent(this, HoraActivity.class);
                    intent.putExtra("dia", "miercoles");
                    startActivity(intent);
                });
                btnJueves.setOnClickListener(v -> {
                    Intent intent = new Intent(this, HoraActivity.class);
                    intent.putExtra("dia", "jueves");
                    startActivity(intent);
                });
                btnViernes.setOnClickListener(v -> {
                    Intent intent = new Intent(this, HoraActivity.class);
                    intent.putExtra("dia", "viernes");
                    startActivity(intent);
                });
                btnSabado.setOnClickListener(v -> {
                    Intent intent = new Intent(this, HoraActivity.class);
                    intent.putExtra("dia", "sabado");
                    startActivity(intent);
                });
                btnBack2.setOnClickListener(v -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                });



            }
        }
