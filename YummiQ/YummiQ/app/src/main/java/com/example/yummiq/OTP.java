package com.example.yummiq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class OTP extends AppCompatActivity {

    ArrayList arr_name,arr_price,arr_qty;
    DatabaseReference ref,get_item_count;
    FirebaseDatabase database;
    TextView text_show_otp;
    String cost;
    boolean ch = false;
    String formated_otp=" ";
    TextView txt_price;
    RecyclerView rec_OTP;
    ImageView share;
    String otpString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp);

        text_show_otp = findViewById(R.id.text_show_otp);
        rec_OTP = findViewById(R.id.recyclerview_otp);
        share = findViewById(R.id.img_share);

        SecureRandom secureRandom = new SecureRandom();

        arr_name = getIntent().getStringArrayListExtra("name");
        arr_qty = getIntent().getStringArrayListExtra("qty");
        arr_price = getIntent().getStringArrayListExtra("price");
        cost = getIntent().getStringExtra("total");


        database = FirebaseDatabase.getInstance();
        ref = database.getReference("orders");
        get_item_count = database.getReference("rit");

        get_item_count.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String get_name = snapshot.child("name").getValue().toString();
                Toast.makeText(OTP.this, get_name, Toast.LENGTH_SHORT).show();

                if(arr_name.contains(get_name)){

                    String key = snapshot.getKey();
                    String get_count = snapshot.child("count").getValue().toString();
                    HashMap temp_map = new HashMap();
                    temp_map.put("count",Integer.valueOf(get_count)-Integer.parseInt(arr_qty.get(arr_name.indexOf(get_name)).toString()));
                    DatabaseReference temp_ref = get_item_count.child(key);
                    temp_ref.updateChildren(temp_map).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            Toast.makeText(OTP.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hi greetings from Yummiq");
                startActivity(Intent.createChooser(shareIntent, "Share code via"));
            }
        });

        rec_OTP.setLayoutManager(new LinearLayoutManager(this));
        rec_OTP.setAdapter(new Bill_adapter(getApplicationContext(), arr_name, arr_qty, arr_price));


        // Generate a random number between 1000 and 9999 (inclusive)
        int otp = 1000 + secureRandom.nextInt(9000);

        // Convert the OTP to a string if needed
        otpString = String.valueOf(otp);

        for (int i = 0; i < otpString.length(); i++) {
            formated_otp += String.valueOf(otpString.charAt(i))+"  " ;
        }


        text_show_otp.setText(formated_otp);




        HashMap map = new HashMap<>();


        map.put("name",arr_name.toString());
        map.put("qty",arr_qty.toString());
        map.put("price",arr_price.toString());
        map.put("cost",cost);
        map.put("otp",otpString);

        ref.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                text_show_otp.setText(formated_otp);
                ch = true;
            }
        });









    }

    @Override
    protected void onStart() {
        super.onStart();





    }
}