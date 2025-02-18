package com.example.yummiq;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList arr_name,arr_price,arr_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.search_recyclerview);

        arr_name = new ArrayList();
        arr_price = new ArrayList();
        arr_url = new ArrayList();

        arr_name = getIntent().getStringArrayListExtra("name");
        arr_price = getIntent().getStringArrayListExtra("price");
        arr_url = getIntent().getStringArrayListExtra("url");

        recyclerView.setLayoutManager(new LinearLayoutManager(Search.this));
        recyclerView.setAdapter(new Search_adapter(Search.this, arr_name, arr_price,arr_url,arr_price));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList arr_fil_name = new ArrayList();
                ArrayList arr_fill_price = new ArrayList();
                ArrayList arr_fill_url = new ArrayList();

                for (int i = 0; i < arr_name.size(); i++) {
                    if (arr_name.get(i).toString().toLowerCase().contains(query.toLowerCase())) {
                        arr_fil_name.add(arr_name.get(i));
                        arr_fill_price.add(arr_price.get(i));
                        arr_fill_url.add(arr_url.get(i));
                    }

                }
                if (arr_fil_name.size() == 0) {
                    arr_fill_price.clear();
                    arr_fil_name.clear();
                    recyclerView.setLayoutManager(new LinearLayoutManager(Search.this));
                    recyclerView.setAdapter(new Search_adapter(Search.this, arr_fil_name, arr_fill_price,arr_fill_url,arr_fill_price));
                    Toast.makeText(Search.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    recyclerView.setLayoutManager(new LinearLayoutManager(Search.this));
                    recyclerView.setAdapter(new Search_adapter(Search.this, arr_fil_name, arr_fill_price,arr_fill_url,arr_fill_price));
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList arr_fil_name = new ArrayList();
                ArrayList arr_fill_price = new ArrayList();
                ArrayList arr_fill_url = new ArrayList();


                for (int i = 0; i < arr_name.size(); i++) {
                    if (arr_name.get(i).toString().toLowerCase().contains(newText.toLowerCase())) {
                        arr_fil_name.add(arr_name.get(i));
                        arr_fill_price.add(arr_price.get(i));
                        arr_fill_url.add(arr_url.get(i));

                    }
                }

                if (arr_fil_name.size() == 0) {
                    arr_fill_price.clear();
                    arr_fil_name.clear();
                    recyclerView.setLayoutManager(new LinearLayoutManager(Search.this));
                    recyclerView.setAdapter(new Search_adapter(Search.this, arr_fil_name, arr_fill_price,arr_fill_url,arr_fill_price));
                    Toast.makeText(Search.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    recyclerView.setLayoutManager(new LinearLayoutManager(Search.this));
                    recyclerView.setAdapter(new Search_adapter(Search.this, arr_fil_name, arr_fill_price,arr_fill_url,arr_fill_price));
                }
                return true;
            }
        });



    }
}