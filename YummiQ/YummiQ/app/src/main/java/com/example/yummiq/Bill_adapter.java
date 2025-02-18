package com.example.yummiq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Bill_adapter extends RecyclerView.Adapter<Bill_holder> {

    Context context;
    List<String> list_name,list_qty,list_price;

    public Bill_adapter(Context context, List<String> list_name, List<String> list_qty, List<String> list_price) {
        this.context = context;
        this.list_name = list_name;
        this.list_qty = list_qty;
        this.list_price = list_price;
    }

    @NonNull
    @Override
    public Bill_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Bill_holder(LayoutInflater.from(context).inflate(R.layout.bill_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Bill_holder holder, int position) {

        holder.text_name.setText(list_name.get(position).toString());
        holder.text_qty.setText(list_qty.get(position).toString());
        holder.text_price.setText(String.valueOf(Integer.valueOf(list_price.get(position))*Integer.valueOf(list_qty.get(position))));

    }

    @Override
    public int getItemCount() {
        return list_name.size();
    }
}
