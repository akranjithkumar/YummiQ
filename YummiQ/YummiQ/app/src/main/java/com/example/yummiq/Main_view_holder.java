package com.example.yummiq;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class Main_view_holder extends RecyclerView.ViewHolder {

    TextView text_category;
    RecyclerView main_recycle;
    ConstraintLayout constraintLayout;

    public Main_view_holder(@NonNull View itemView) {
        super(itemView);

        text_category = itemView.findViewById(R.id.list_text_category);
        main_recycle = itemView.findViewById(R.id.cat_list_layout);

        constraintLayout = itemView.findViewById(R.id.global_click_layout);

    }
}