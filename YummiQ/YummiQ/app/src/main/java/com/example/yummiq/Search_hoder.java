package com.example.yummiq;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Search_hoder extends RecyclerView.ViewHolder {

    TextView search_text_count,search_text_name,search_text_price;
    LinearLayout search_minus,search_plus;
    ImageView img_search_food;

    public Search_hoder(@NonNull View itemView) {
        super(itemView);

        search_text_count = itemView.findViewById(R.id.search_txt_count_items);
        search_text_name = itemView.findViewById(R.id.search_text_name);
        search_text_price = itemView.findViewById(R.id.search_text_price);

        search_minus = itemView.findViewById(R.id.search_minus_bn);
        search_plus = itemView.findViewById(R.id.search_plus_btn);
        img_search_food = itemView.findViewById(R.id.img_search_food);

    }
}
