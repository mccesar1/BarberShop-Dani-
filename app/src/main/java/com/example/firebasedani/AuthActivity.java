package com.example.firebasedani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtPassword = findViewById(R.id.txtPassword);

        Button btnEntrar = findViewById(R.id.btnEntrar);
        Button btnRegister = findViewById(R.id.btnRegister);
        onStart();
        btnEntrar.setOnClickListener(v -> {

            if (txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, "Ingrese su email y contraseña", Toast.LENGTH_SHORT).show();
                return;
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        showHome();
                    } else {
                        Toast.makeText(this, "ERROR usuario no registrado", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(AuthActivity.this, RegisterActivity.class));
            finish();
        });
    }
    private void showHome(){
        startActivity(new Intent(AuthActivity.this, MainActivity.class));
    }
    //gaurdar datos de sesion
    @Override
    protected void onStart() {
        super.onStart();
            if (mAuth.getCurrentUser() != null){
            showHome();
        }
    }



}