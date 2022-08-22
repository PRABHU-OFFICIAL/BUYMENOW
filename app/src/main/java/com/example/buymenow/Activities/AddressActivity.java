package com.example.buymenow.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.buymenow.Adapters.AddressAdapter;
import com.example.buymenow.Models.AddressModel;
import com.example.buymenow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress {

    Button btnPayment , btnAddAddress;
    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdapter addressAdapter;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String myAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        btnAddAddress = findViewById(R.id.add_address_btn);
        btnPayment = findViewById(R.id.payment_btn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.address_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressModelList = new ArrayList<>();
        addressAdapter = new AddressAdapter(getApplicationContext() , addressModelList , this);
        recyclerView.setAdapter(addressAdapter);

        fStore.collection("CurrentUser").document(fAuth.getCurrentUser().getUid())
                        .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot snapshot : task.getResult().getDocuments()){
                                AddressModel addressModel = snapshot.toObject(AddressModel.class);
                                addressModelList.add(addressModel);
                                addressAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });


        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this , AddAddressActivity.class));
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressActivity.this , PaymentSuccess.class));
                final MediaPlayer mp = MediaPlayer.create(getApplicationContext() , R.raw.paytune);
                mp.start();
                finish();
            }
        });
    }

    @Override
    public void setAddress(String address) {
        myAddress = address;
    }
}