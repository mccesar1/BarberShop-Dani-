package com.example.firebasedani;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public FirebaseDatabase db = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnApartar = findViewById(R.id.btnApartar);
        Button btnCerrar = findViewById(R.id.btnCerrar);
        Button btnCancelar = findViewById(R.id.btnCancelar);

        TextView cita = findViewById(R.id.txtCita);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("reservaciones").child("sabado").get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                DataSnapshot document = task.getResult();
                if (document.exists() && document.child("5").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getDisplayName())) {
                    for (DataSnapshot dia : document.getChildren()) {
                        System.out.println(document.getKey());
                        if (dia.getValue().equals(FirebaseAuth.getInstance().getCurrentUser().getDisplayName())) {
                            cita.setText("Tu cita es el "+document.getKey() +" a las: "+dia.getKey());
                            
//                            System.out.println(dia.getValue() + dia.getKey());
                        }
                    }
                }else{
                    cita.setText("No tienes citas");
//                    System.out.println(document.getValue());
//                    System.out.println(document.child("4").getValue().toString());

                }

            }
        });
        btnCancelar.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea cancelar su cita?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            //delete cita
                            FirebaseDatabase db = FirebaseDatabase.getInstance();
                            db.getReference().child("reservaciones").child("lunes").child("4").setValue("");
                            startActivity(intent);
                            cita.setText("No tienes citas");

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        });




//        DataSnapshot document = ref.get().getResult();
//        if (document.exists()) {
//            cita.setText("Tu cita es el lunes a las " + document.getValue().toString());
//        } else {
//            cita.setText("No tienes cita");
//        }


        btnApartar.setOnClickListener(v -> {
            showApartar();
        });

          btnCerrar.setOnClickListener(v -> {
                FirebaseAuth.getInstance().signOut();
                showAuth();
            });

    }
    private void showApartar(){
        startActivity(new Intent(MainActivity.this, ApartarActivity.class));
    }

    public void showAuth(){
        startActivity(new Intent(MainActivity.this, AuthActivity.class));
        FirebaseAuth.getInstance().signOut();
    }
}