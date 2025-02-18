package com.example.yummiq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Main_adapter extends RecyclerView.Adapter<Main_view_holder> {

    Context context;
    List<String> cat_list,removed_cat_list,price_list,arr_url,arr_count;
    List<String> item_name_list;
    Sub_adapter subAdapter;
    ArrayList sorted_name_list,sorted_price_list;

    public Main_adapter(Context context, List<String> removed_cat_list, ArrayList cat_list, ArrayList item_name_list, ArrayList price_list, ArrayList arr_url,ArrayList arr_count) {
        this.context = context;
        this.removed_cat_list = removed_cat_list;
        this.cat_list = cat_list;
        this.arr_count = arr_count;
        this.item_name_list = item_name_list;
        this.price_list = price_list;
        sorted_name_list = new ArrayList<>();
        sorted_price_list = new ArrayList<>();
        this.arr_url = arr_url;
    }

    @NonNull
    @Override
    public Main_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Main_view_holder(LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Main_view_holder holder, int position) {

        holder.text_category.setText(removed_cat_list.get(position));
        subAdapter = new Sub_adapter(context,item_name_list, (ArrayList) price_list,cat_list,removed_cat_list.get(position).toString(), (ArrayList) arr_url,arr_count);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.main_recycle.setLayoutManager(layoutManager);
        holder.main_recycle.setAdapter(subAdapter);

    }



    @Override
    public int getItemCount() {
        return removed_cat_list.size();
    }
}
