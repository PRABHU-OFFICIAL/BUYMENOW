package com.example.buymenow.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.buymenow.Activities.PaymentActivity;
import com.example.buymenow.Adapters.CartAdapter;
import com.example.buymenow.Models.CartModel;
import com.example.buymenow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    List<CartModel> cartModelList;
    CartAdapter cartAdapter;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    int overallTotalAmount;
    TextView overAllAmount;
    Button buyNow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.cartRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        cartModelList = new ArrayList<>();
        cartAdapter = new CartAdapter(requireActivity() , cartModelList);
        recyclerView.setAdapter(cartAdapter);
        overAllAmount = view.findViewById(R.id.value);
        buyNow = view.findViewById(R.id.buyNow);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // Receiving data from Adapter
        LocalBroadcastManager.getInstance(requireActivity())
                        .registerReceiver(mMessageReceiver , new IntentFilter("MyTotalAmount"));

        fStore.collection("Add To Cart").document(fAuth.getCurrentUser().getUid())
                .collection("user").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                                String documentId = documentSnapshot.getId();
                                CartModel cartModel = new CartModel(documentSnapshot.getString("CurrentTime") , documentSnapshot.getString("CurrentDate"), documentSnapshot.getString("ProductName") , documentSnapshot.getString("ProductPrice"));
                                cartModel.setDocumentId(documentId);
                                cartModelList.add(cartModel);
                                cartAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity() , PaymentActivity.class);
                intent.putExtra("subtotal" , overAllAmount.getText().toString());
                startActivity(intent);
            }
        });

    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = 0;
            int k = Integer.parseInt(overAllAmount.getText().toString());
            String str = intent.getStringExtra("totalAmount");
            String[] array = str.split(" ");
            totalBill = k + Integer.parseInt(array[1]);
            overAllAmount.setText(String.valueOf(totalBill));
        }
    };
}