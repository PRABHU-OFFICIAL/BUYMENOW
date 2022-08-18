package com.example.buymenow.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buymenow.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText loginEmail , loginPassword;
    TextView registerText;
    Button btnLogin;
    FirebaseAuth fAuth;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPassword = findViewById(R.id.loginPassword);
        loginEmail = findViewById(R.id.loginEmail);
        registerText = findViewById(R.id.registerText);
        btnLogin = findViewById(R.id.btnLogin);

        fAuth = FirebaseAuth.getInstance();

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this , Register.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkValidity(loginEmail);
                checkValidity(loginPassword);

                if (valid)
                    fAuth.signInWithEmailAndPassword(loginEmail.getText().toString(), loginPassword.getText().toString())
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(Login.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
            }
        });
    }
    private boolean checkValidity(EditText text){
        if (text.getText().toString().isEmpty()){
            text.setError("Error");
            valid = false;
        }else{
            valid = true;
        }
        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(Login.this , MainActivity.class));
            finish();
        }
    }
}