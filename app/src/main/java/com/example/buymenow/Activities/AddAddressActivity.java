package com.example.buymenow.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buymenow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    EditText name , address, city , postalCode , phoneNumber;
    Button btnAddAddress;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        name = findViewById(R.id.ad_name);
        address = findViewById(R.id.ad_address);
        city = findViewById(R.id.ad_city);
        phoneNumber = findViewById(R.id.ad_phone);
        postalCode = findViewById(R.id.ad_code);
        btnAddAddress = findViewById(R.id.ad_add_address);

        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = name.getText().toString();
                String userCity = city.getText().toString();
                String userAddress = address.getText().toString();
                String userCode = postalCode.getText().toString();
                String userNumber = phoneNumber.getText().toString();

                fAuth = FirebaseAuth.getInstance();
                fStore = FirebaseFirestore.getInstance();

                String finalAddress = "";

                if (!userName.isEmpty()){
                    finalAddress += userName;
                }
                if (!userAddress.isEmpty()){
                    finalAddress += userAddress;
                }
                if (!userCity.isEmpty()){
                    finalAddress += userCity;
                }
                if (!userNumber.isEmpty()){
                    finalAddress += userNumber;
                }
                if (!userCode.isEmpty()){
                    finalAddress += userCode;
                }


                if (!userName.isEmpty() && !userCity.isEmpty() && !userAddress.isEmpty() && !userCode.isEmpty() && !userNumber.isEmpty()){

                    Map<String , String> map = new HashMap<>();
                    map.put("userAddress" , finalAddress);

                    fStore.collection("CurrentUser").document(fAuth.getCurrentUser().getUid())
                            .collection("Address").add(map)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(AddAddressActivity.this, "Address added Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(AddAddressActivity.this, "Kindly fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}