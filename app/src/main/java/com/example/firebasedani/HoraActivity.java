package com.example.firebasedani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora);
        TextView txtDia = findViewById(R.id.txtDia);
        Button btn3 = findViewById(R.id.btn3);
        Button btn35 = findViewById(R.id.btn35);
        Button btn4 = findViewById(R.id.btn4);
        Button btn45 = findViewById(R.id.btn45);
        Button btn5 = findViewById(R.id.btn5);
        Button btn55 = findViewById(R.id.btn55);
        Button btn6 = findViewById(R.id.btn6);
        Button btn65 = findViewById(R.id.btn65);
        Button btn7 = findViewById(R.id.btn7);
        Button btn75 = findViewById(R.id.btn75);
        Intent intent = getIntent();
        String dia = intent.getStringExtra("dia");
        txtDia.setText(dia);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoraActivity.this, ApartarActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(v -> {
           //if data is empty then save data
            db.getReference().child("reservaciones").child(dia).child("3").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getValue() != null){
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        Toast.makeText(HoraActivity.this, "Ya existe una cita a esa hora", Toast.LENGTH_SHORT).show();
                    }else{
                        db.getReference().child("reservaciones").child(dia).child("3").setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                        btn45.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        Toast.makeText(HoraActivity.this, "Cita guardada", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        });
        btn35.setOnClickListener(v -> {
            db.getReference().child("reservaciones").child(dia).child("3:30").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null){
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                    Toast.makeText(HoraActivity.this, "Ya existe una cita a esa hora", Toast.LENGTH_SHORT).show();
                }else{
                        btn35.setBackgroundColor(Color.RED);
                        btn45.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                        btn35.setEnabled(false);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    });
        btn4.setOnClickListener(v -> {
            db.getReference().child("reservaciones").child(dia).child("4").setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            btn4.setBackgroundColor(Color.RED);
            btn45.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            btn4.setEnabled(false);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
        btn45.setOnClickListener(v -> {
            db.getReference().child("reservaciones").child(dia).child("4:30").setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            btn45.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            btn45.setBackgroundColor(Color.RED);
            btn45.setEnabled(false);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });
        btn5.setOnClickListener(v -> {
            db.getReference().child("reservaciones").child(dia).child("5").setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            btn5.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            btn5.setBackgroundColor(Color.RED);
            btn5.setEnabled(false);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        });

        db.getReference().child("reservaciones").child(dia).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot document = task.getResult();
                for (DataSnapshot hora : document.getChildren()) {
                    if (hora.getKey().toString().isEmpty()) {
                        btn3.setEnabled(true);
                        btn3.setBackgroundColor(Color.parseColor("#71e676"));
                        btn4.setEnabled(true);
                        btn4.setBackgroundColor(Color.parseColor("#71e676"));
                        btn5.setEnabled(true);
                        btn5.setBackgroundColor(Color.parseColor("#71e676"));
                        btn6.setEnabled(true);
                        btn6.setBackgroundColor(Color.parseColor("#71e676"));
                    } else if (!hora.getKey().toString().isEmpty()) {
                        if (hora.getKey().equals("3") && hora.getValue().toString().isEmpty()) {
                            btn3.setBackgroundColor(Color.parseColor("#71e676"));
                            btn3.setEnabled(true);
                        } else if (hora.getKey().equals("3") && !hora.getValue().toString().isEmpty()) {
                            btn3.setText(hora.getValue().toString());
                            btn3.setBackgroundColor(Color.parseColor("#A6ada6"));
                            btn3.setEnabled(false);
                        } else if (hora.getKey().equals("3:30") && hora.getValue().toString().isEmpty()) {
                            btn35.setBackgroundColor(Color.parseColor("#71e676"));
                            btn35.setEnabled(true);
                        } else if (hora.getKey().equals("3:30") && !hora.getValue().toString().isEmpty()) {
                            btn35.setText(hora.getValue().toString());
                            btn35.setBackgroundColor(Color.parseColor("#A6ada6"));
                            btn35.setEnabled(false);
                        } else if (hora.getKey().equals("4") && hora.getValue().toString().isEmpty()) {
                            btn4.setBackgroundColor(Color.parseColor("#71e676"));
                            btn4.setEnabled(true);
                        } else if (hora.getKey().equals("4") && !hora.getValue().toString().isEmpty()) {
                            btn4.setText(hora.getValue().toString());
                            btn4.setBackgroundColor(Color.parseColor("#A6ada6"));
                            btn4.setEnabled(false);
                        } else if (hora.getKey().equals("4:30") && hora.getValue().toString().isEmpty()) {
                            btn45.setBackgroundColor(Color.parseColor("#71e676"));
                            btn45.setEnabled(true);
                        } else if (hora.getKey().equals("4:30") && !hora.getValue().toString().isEmpty()) {
                            btn45.setText(hora.getValue().toString());
                            btn45.setBackgroundColor(Color.parseColor("#A6ada6"));
                            btn45.setEnabled(false);
                        } else if (hora.getKey().equals("5") && hora.getValue().toString().isEmpty()) {
                            btn5.setBackgroundColor(Color.parseColor("#71e676"));
                            btn5.setEnabled(true);
                        } else if (hora.getKey().equals("5") && !hora.getValue().toString().isEmpty()) {
                            btn5.setText(hora.getValue().toString());
                            btn5.setBackgroundColor(Color.parseColor("#A6ada6"));
                            btn5.setEnabled(false);
                        } else if (hora.getKey().equals("5:30") && hora.getValue().toString().isEmpty()) {
                            btn55.setBackgroundColor(Color.parseColor("#71e676"));
                            btn55.setEnabled(true);
                        } else if (hora.getKey().equals("5:30") && !hora.getValue().toString().isEmpty()) {
                            btn55.setText(hora.getValue().toString());
                            btn55.setBackgroundColor(Color.parseColor("#A6ada6"));
                            btn55.setEnabled(false);
                        } else if (hora.getKey().equals("6") && hora.getValue().toString().isEmpty()) {
                            btn6.setBackgroundColor(Color.parseColor("#71e676"));
                            btn6.setEnabled(true);
                        } else if (hora.getKey().equals("6") && !hora.getValue().toString().isEmpty()) {
                            btn6.setText(hora.getValue().toString());
                            btn6.setBackgroundColor(Color.parseColor("#A6ada6"));
                            btn6.setEnabled(false);
                        } else if (hora.getKey().equals("6:30") && hora.getValue().toString().isEmpty()) {
                            btn65.setBackgroundColor(Color.parseColor("#71e676"));
                            btn65.setEnabled(true);
                        } else if (hora.getKey().equals("6:30") && !hora.getValue().toString().isEmpty()) {
                            btn65.setText(hora.getValue().toString());
                            btn65.setBackgroundColor(Color.parseColor("#A6ada6"));
                            btn65.setEnabled(false);
                        } else if (hora.getKey().equals("7") && hora.getValue().toString().isEmpty()) {
                            btn7.setBackgroundColor(Color.parseColor("#71e676"));
                            btn7.setEnabled(true);
                        } else if (hora.getKey().equals("7") && !hora.getValue().toString().isEmpty()) {
                            btn7.setText(hora.getValue().toString());
                            btn7.setBackgroundColor(Color.parseColor("#A6ada6"));
                            btn7.setEnabled(false);
                        } else if (hora.getKey().equals("7:30") && hora.getValue().toString().isEmpty()) {
                            btn75.setBackgroundColor(Color.parseColor("#71e676"));
                            btn75.setEnabled(true);
                        } else if (hora.getKey().equals("7:30") && !hora.getValue().toString().isEmpty()) {
                            btn75.setText(hora.getValue().toString());
                            btn75.setBackgroundColor(Color.parseColor("#A6ada6"));
                            btn75.setEnabled(false);
                        }


                    }
                }
            }
        });
    }
}

