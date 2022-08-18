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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText registerUserName , registerEmail, registerMobile, registerPassword ;
    Button btnRegister;
    TextView loginText;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerUserName = findViewById(R.id.registerUserName);
        registerEmail = findViewById(R.id.registerEmail);
        registerMobile = findViewById(R.id.registerMobile);
        registerPassword = findViewById(R.id.registerPassword);
        btnRegister = findViewById(R.id.btnRegister);
        loginText = findViewById(R.id.loginText);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this , Login.class));
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidity(registerUserName);
                checkValidity(registerEmail);
                checkValidity(registerMobile);
                checkValidity(registerPassword);

                if(valid){
                    fAuth.createUserWithEmailAndPassword(registerEmail.getText().toString() , registerPassword.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    FirebaseUser user = fAuth.getCurrentUser();
                                    Toast.makeText(Register.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    assert user != null;
                                    DocumentReference df = fStore.collection("Users").document(user.getUid());
                                    Map<String , Object> userInfo = new HashMap<>();
                                    userInfo.put("UserName" , registerUserName.getText().toString());
                                    userInfo.put("UserEmail" , registerEmail.getText().toString());
                                    userInfo.put("Mobile" , registerMobile.getText().toString());
                                    userInfo.put("Password" , registerPassword.getText().toString());
                                    df.set(userInfo);
                                    startActivity(new Intent(getApplicationContext() , MainActivity.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this, "Failed to create Account", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
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
}