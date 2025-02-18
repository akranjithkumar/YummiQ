package com.example.yummiq;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Sub_view_holder extends RecyclerView.ViewHolder {

    TextView text_food,text_price,txt_count;
    LinearLayout btn_plus,btn_minus;
    ImageView img_food;

    public Sub_view_holder(@NonNull View itemView) {
        super(itemView);

        text_food = itemView.findViewById(R.id.text_food);
        text_price = itemView.findViewById(R.id.text_price);
        txt_count = itemView.findViewById(R.id.txt_count_items);
        btn_plus = itemView.findViewById(R.id.plus_btn);
        btn_minus = itemView.findViewById(R.id.minus_btn);
        img_food = itemView.findViewById(R.id.img_food);


    }
}