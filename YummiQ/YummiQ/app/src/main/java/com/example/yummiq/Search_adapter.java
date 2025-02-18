package com.example.yummiq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Search_adapter extends RecyclerView.Adapter<Search_hoder> {

    Context context;
    ArrayList arr_name,arr_price,arr_url,arr_count;
    DBhelper dBhelper;

    public Search_adapter(Context context, ArrayList arr_name, ArrayList arr_price,ArrayList arr_url,ArrayList arr_count) {
        this.context = context;
        this.arr_name = arr_name;
        this.arr_price = arr_price;
        this.arr_url = arr_url;
        this.arr_count = arr_count;
        dBhelper = new DBhelper(context);
    }

    @NonNull
    @Override
    public Search_hoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Search_hoder(LayoutInflater.from(context).inflate(R.layout.search_tile, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Search_hoder holder, int position) {
        holder.search_text_name.setText(arr_name.get(position).toString());
        final int[] count = {0};

        Glide.with(context)
                .load(arr_url.get(position).toString())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.img_search_food);

        holder.search_text_name.setText(arr_name.get(position).toString());
        holder.search_text_price.setText(arr_price.get(position).toString());

        holder.search_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0] +=1;

                holder.search_text_count.setText(String.valueOf(count[0]));

                int c =dBhelper.getUser(arr_name.get(position).toString(),context);
                if (c > 0) {

                    dBhelper.updateItem(arr_name.get(position).toString(),arr_price.get(position).toString(),String.valueOf(count[0]),context);

                }
                else {
                    long in = dBhelper.insertUser(arr_name.get(position).toString(),arr_price.get(position).toString(),String.valueOf(count[0]),context);
                }




            }
        });
        holder.search_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0] -=1;
                if(count[0] <=0){
                    count[0] =0;
                    dBhelper.deleteRow(arr_name.get(position).toString());
                }else {
                    holder.search_text_count.setText(String.valueOf(count[0]));

                    int c =dBhelper.getUser(arr_name.get(position).toString(),context);
                    if (c > 0) {

                        dBhelper.updateItem(arr_name.get(position).toString(),arr_price.get(position).toString(),String.valueOf(count[0]),context);

                    }
                    else {
                        long in = dBhelper.insertUser(arr_name.get(position).toString(),arr_price.get(position).toString(),String.valueOf(count[0]),context);
                    }

                }
                holder.search_text_count.setText(String.valueOf(count[0]));


            }
        });    }

    @Override
    public int getItemCount() {
        return arr_name.size();
    }
}
