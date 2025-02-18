package com.example.yummiq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Sub_adapter extends RecyclerView.Adapter<Sub_view_holder> {

    Context context;
    List<String> dataList,price_list,cat_list,removed_item_list,removed_price_list,arr_url,removed_url,arr_count;
    String cat_type;
    int main_pos;
    TinyDB tinyDB;
    DBhelper dBhelper;


    public Sub_adapter(Context context, List<String> dataList, ArrayList price_list, List<String> cat_list, String cat_type, ArrayList arr_url, List<String> arr_count) {
        this.context = context;
        this.dataList = dataList;
        this.cat_type = cat_type;
        this.price_list = price_list;
        this.arr_url = arr_url;
        this.cat_list = cat_list;
        this.arr_count  = arr_count;
        removed_item_list = new ArrayList<>();
        removed_price_list = new ArrayList<>();
        removed_url = new ArrayList<>();
        tinyDB = new TinyDB(context);
        dBhelper = new DBhelper(context);


        for (int i = 0; i < dataList.size(); i++) {
            if (cat_list.get(i).equals(cat_type) ){
                removed_item_list.add(dataList.get(i));
                removed_price_list.add(price_list.get(i).toString());
                removed_url.add(arr_url.get(i).toString());

            }
        }
    }

    @NonNull
    @Override
    public Sub_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Sub_view_holder(LayoutInflater.from(context).inflate(R.layout.tile, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Sub_view_holder holder, int position) {



        if(0 < Integer.parseInt(arr_count.get(position).toString())){
            final int[] count = {0};

            holder.text_food.setText(removed_item_list.get(position));
            holder.text_price.setText(removed_price_list.get(position));
            Glide.with(context)
                    .load(removed_url.get(position).toString())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.img_food);

            holder.btn_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count[0] +=1;
                    holder.txt_count.setText(String.valueOf(count[0]));

                    int c =dBhelper.getUser(removed_item_list.get(position),context);
                    if (c > 0) {

                        dBhelper.updateItem(removed_item_list.get(position).toString(),removed_price_list.get(position).toString(),String.valueOf(count[0]),context);

                    }
                    else {
                        long in = dBhelper.insertUser(removed_item_list.get(position).toString(),removed_price_list.get(position).toString(),String.valueOf(count[0]),context);
                    }




                }
            });
            holder.btn_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count[0] -=1;
                    if(count[0] <=0){
                        count[0] =0;
                        dBhelper.deleteRow(removed_item_list.get(position).toString());
                    }else {
                        holder.txt_count.setText(String.valueOf(count[0]));

                        int c =dBhelper.getUser(removed_item_list.get(position),context);
                        if (c > 0) {

                            dBhelper.updateItem(removed_item_list.get(position).toString(),removed_price_list.get(position).toString(),String.valueOf(count[0]),context);

                        }
                        else {
                            long in = dBhelper.insertUser(removed_item_list.get(position).toString(),removed_price_list.get(position).toString(),String.valueOf(count[0]),context);
                        }

                    }
                    holder.txt_count.setText(String.valueOf(count[0]));


                }
            });

        }
        else {
            holder.img_food.setAlpha(0.2f);
            holder.text_price.setAlpha(0.5f);
            holder.text_food.setAlpha(0.5f);
            holder.btn_plus.setAlpha(0.5f);
            holder.btn_minus.setAlpha(0.5f);
            holder.txt_count.setAlpha(0.5f);

            holder.text_food.setText(removed_item_list.get(position));
            holder.text_price.setText(removed_price_list.get(position));
            Glide.with(context)
                    .load(removed_url.get(position).toString())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.img_food);



        }


    }

    @Override
    public int getItemCount() {
        return removed_item_list.size();
    }


}
