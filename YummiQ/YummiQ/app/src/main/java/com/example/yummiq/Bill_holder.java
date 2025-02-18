package com.example.yummiq;

import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Bill_holder extends RecyclerView.ViewHolder {

    TextView text_name,text_qty,text_price;

    public Bill_holder(@NonNull View itemView) {
        super(itemView);

        text_name = itemView.findViewById(R.id.bill_text_name);
        text_qty = itemView.findViewById(R.id.bill_text_qty);
        text_price = itemView.findViewById(R.id.bil_text_price);



    }
}
