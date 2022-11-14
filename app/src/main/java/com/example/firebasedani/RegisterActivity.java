package com.example.firebasedani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText txtRuser = findViewById(R.id.txtRuser);
        EditText txtRpassword = findViewById(R.id.txtRpassword);
        EditText txtRpassword2 = findViewById(R.id.txtRpassword2);
        Button btnRegister = findViewById(R.id.btnRegister);
        EditText email = findViewById(R.id.txtEmail);

        //register
        btnRegister.setOnClickListener(v -> {
            if (email.getText().toString().isEmpty() || txtRpassword.getText().toString().isEmpty()) {
                return;
            }else if(!txtRpassword.getText().toString().equals(txtRpassword2.getText().toString())){
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();

            }else if(txtRpassword.getText().toString().length()<8) {
                Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }else if( !email.getText().toString().contains("@") || !email.getText().toString().contains(".")){
                Toast.makeText(this, "El correo debe tener un formato valido", Toast.LENGTH_SHORT).show();
                return;

            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), txtRpassword.getText().toString()).addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(txtRuser.getText().toString())
                                .build();
                        FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileUpdates);


                       Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                        txtRuser.setText("");
                        txtRpassword.setText("");
                        txtRpassword2.setText("");
                        email.setText("");
                        startActivity(new Intent(RegisterActivity.this, AuthActivity.class));
                        //save username in database

                    } else {
                        Toast.makeText(this, "ERROR usuario ya registrado", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

//    firebase.auth().createUserWithEmailAndPassword(correo, contra).then(function(user) {
//
//        user.updateProfile({
//                //aqui guardas los componentes
//                displayName: username
//
//                                      }).then(function() {
//            alert();
//        }, function(error) {
//            swal(error);
//        });
//    }, function(error) {
//        // Handle Errors here.
//        var errorCode = error.code;
//        var errorMessage = error.message;
//        // [START_EXCLUDE]
//        if (errorCode == 'auth/weak-password') {
//            swal('La contraseña es muy corta');
//        } else {
//            swal(errorMessage);
//        }
//        // [END_EXCLUDE]
//    });

    //volver a la pantalla de login



}