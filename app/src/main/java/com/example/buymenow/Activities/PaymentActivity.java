package com.example.buymenow.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.buymenow.R;

public class PaymentActivity extends AppCompatActivity {

    TextView sub_total , dis_count , ship_ping , amount;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        sub_total = findViewById(R.id.sub_total);
        dis_count = findViewById(R.id.dis_count);
        ship_ping = findViewById(R.id.ship_ping);
        amount = findViewById(R.id.amount);
        btnPay = findViewById(R.id.btnPay);

        Intent intent = getIntent();
        sub_total.setText("Rs " + intent.getStringExtra("subtotal"));
        dis_count.setText("Rs 100");
        ship_ping.setText("Rs 20");
        int result = Integer.parseInt(intent.getStringExtra("subtotal")) - 80;
        amount.setText(String.valueOf(result));

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this , AddressActivity.class));
            }
        });
    }


}