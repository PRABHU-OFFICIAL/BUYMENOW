package com.example.buymenow.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buymenow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphTextView;

public class Detailed extends AppCompatActivity implements Serializable {

    ImageView detailImage, ramIcon , procIcon , camIcon ,batIcon;
    NeumorphTextView detailName;
    TextView itemPrice , ramText , proctext , camText , batText;
    NeumorphButton addCart ;
    int icon1 , icon2 , icon3 , icon4;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        detailName = findViewById(R.id.detailName);
        detailImage = findViewById(R.id.detailImage);
        itemPrice = findViewById(R.id.itemPrice);
        addCart = findViewById(R.id.addCart);

        ramIcon = findViewById(R.id.ramIcon);
        ramText = findViewById(R.id.ramText);
        procIcon = findViewById(R.id.procIcon);
        proctext = findViewById(R.id.procText);
        camIcon = findViewById(R.id.canIcon);
        camText = findViewById(R.id.camText);
        batIcon = findViewById(R.id.batIcon);
        batText = findViewById(R.id.batText);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        detailName.setText(getIntent().getStringExtra("na"));
        String str = getIntent().getStringExtra("img");
        Picasso.get().load(str).into(detailImage);
        itemPrice.setText(getIntent().getStringExtra("pri"));

        ramText.setText(getIntent().getStringExtra("text1"));
        proctext.setText(getIntent().getStringExtra("text2"));
        camText.setText(getIntent().getStringExtra("text3"));
        batText.setText(getIntent().getStringExtra("text4"));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            icon1 = bundle.getInt("icon1");
            icon2 = bundle.getInt("icon2");
            icon3 = bundle.getInt("icon3");
            icon4 = bundle.getInt("icon4");
        }
        ramIcon.setImageResource(icon1);
        procIcon.setImageResource(icon2);
        camIcon.setImageResource(icon3);
        batIcon.setImageResource(icon4);

        // Cart Management
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
    }

    private void addToCart() {
        String saveCurrentTime , saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String , Object> cartMap = new HashMap<>();

        cartMap.put("ProductName" , detailName.getText().toString());
        cartMap.put("ProductPrice" , itemPrice.getText().toString());
        cartMap.put("CurrentTime" , saveCurrentTime);
        cartMap.put("CurrentDate" , saveCurrentDate);

        fstore.collection("Add To Cart").document(fAuth.getCurrentUser().getUid())
                .collection("user").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(Detailed.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}