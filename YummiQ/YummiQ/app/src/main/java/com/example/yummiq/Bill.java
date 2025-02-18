package com.example.yummiq;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Bill extends AppCompatActivity implements PaymentResultListener {

    RecyclerView recyclerView;
    TextView totalPriceTextView,txt_add_items; // Declare TextView
    int cost = 0;
    DBhelper dBhelper;
    ArrayList<String> name, qty, price;
    Button btn_make_payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bill);

        dBhelper = new DBhelper(getApplicationContext());




        recyclerView = findViewById(R.id.recyclerView);
        btn_make_payment = findViewById(R.id.make_payment_button);
        totalPriceTextView = findViewById(R.id.total_text_view); // Initialize TextView
        txt_add_items = findViewById(R.id.txt_add_items);

        name = new ArrayList<>();
        qty = new ArrayList<>();
        price = new ArrayList<>();

        Checkout checkout = new Checkout();

        txt_add_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_make_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject options = new JSONObject();
                    options.put("name",R.string.app_name);
                    options.put("description","Payment for anything");
                    options.put("send_sms_hash",true);
                    options.put("allow_rotation",false);

                    options.put("currency","INR");
                    options.put("prefill.contact","+918148839723");
                    options.put("prefill.email","akranjithkumar03@gmail.com");
                    options.put("amount","1.00");

                    checkout.setKeyID("rzp_test_Xzn7gER2ruTjfU");

                    checkout.open(Bill.this,options);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        Cursor cursor = dBhelper.readTableRaw("food");

        while (cursor.moveToNext()) {
            String namestr = cursor.getString(0);
            String pricestr = cursor.getString(1);
            String qtystr = cursor.getString(2);
            if(!namestr.equals(" ")){
                qty.add(qtystr);
                name.add(namestr);
                price.add(pricestr);
            }

        }

        // Calculate Total Price
        for (int i = 0; i < name.size(); i++) {
            int result = Integer.parseInt(qty.get(i)) * Integer.parseInt(String.valueOf(price.get(i)));
            cost += result;
        }

        // Show Toast Message

        // Update the TextView with total price
        totalPriceTextView.setText("Total : " + String.valueOf(cost));

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Bill_adapter(getApplicationContext(), name, qty, price));
    }

    @Override
    public void onPaymentSuccess(String s) {
        Intent intent = new Intent(getApplicationContext(), OTP.class);
        intent.putExtra("name",name);
        intent.putExtra("qty",qty);
        intent.putExtra("price",price);
        intent.putExtra("total",String.valueOf(cost));

        startActivity(intent);
    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}