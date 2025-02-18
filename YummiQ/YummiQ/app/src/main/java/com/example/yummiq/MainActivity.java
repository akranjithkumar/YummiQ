package com.example.yummiq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.PaymentResultListener;

import java.util.ArrayList;
import java.util.HashSet;


public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    ArrayList arr_cat,arr_name,removed_arr,arr_price,cal_price,arr_url,arr_count;
    RecyclerView recyclerView;
    TinyDB tinyDB;
    DBhelper dBhelper;
    DatabaseReference ref;
    FirebaseDatabase database;
    HashSet<String> set;
    TextView textView;
    Main_adapter adapter;
    String s ="";
    LinearLayout btn_buy;
    ImageView imageView,img_search;


    final int UPI_PAYMENT = 0;

    int m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tinyDB = new TinyDB(getApplicationContext());
        dBhelper = new DBhelper(getApplicationContext());
        img_search = findViewById(R.id.search);

        dBhelper.clearTable("food");

        Intent intent = new Intent(getApplicationContext(),Login.class);

        //startActivity(intent);


        long in = dBhelper.insertUser(" "," "," ",getApplicationContext());



        final Activity activity = null;




        recyclerView = findViewById(R.id.main_recycle);
        textView = findViewById(R.id.text_icon);
        btn_buy = findViewById(R.id.btn_buy);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("rit");

        arr_cat =new ArrayList();
        arr_name =new ArrayList();
        removed_arr =new ArrayList();
        arr_price =new ArrayList();
        cal_price =new ArrayList();
        arr_count = new ArrayList();
        arr_url = new ArrayList();

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_search = new Intent(getApplicationContext(),Search.class);
                if(arr_name.size() != 0){
                    intent_search.putExtra("name",arr_name);
                    intent_search.putExtra("price",arr_price);
                    intent_search.putExtra("url",arr_url);
                }
                startActivity(intent_search);
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Bill.class);
                startActivity(intent);
            }
        });




        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                arr_cat.add(snapshot.child("cat").getValue().toString());
                arr_name.add(snapshot.child("name").getValue().toString());
                arr_price.add(snapshot.child("price").getValue().toString());
                arr_url.add(snapshot.child("url").getValue().toString());
                arr_count.add(snapshot.child("count").getValue().toString());



                set = new HashSet<>(arr_cat);
                removed_arr = new ArrayList(set);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new Main_adapter(getApplicationContext(),removed_arr,arr_cat,arr_name,arr_price,arr_url,arr_count));

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                arr_cat.add(snapshot.child("cat").getValue().toString());
                arr_name.add(snapshot.child("name").getValue().toString());
                arr_price.add(snapshot.child("price").getValue().toString());
                arr_url.add(snapshot.child("url").getValue().toString());
                arr_count.add(snapshot.child("count").getValue().toString());

                set = new HashSet<>(arr_cat);
                removed_arr = new ArrayList(set);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Main_adapter(getApplicationContext(),removed_arr,arr_cat,arr_name,arr_price, arr_url,arr_count);
                recyclerView.setAdapter(adapter);

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






    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "paymyent success" + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "paymyent error" + s, Toast.LENGTH_SHORT).show();


    }
}

