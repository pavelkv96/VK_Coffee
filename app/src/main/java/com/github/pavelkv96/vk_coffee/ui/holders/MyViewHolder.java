package com.github.pavelkv96.vk_coffee.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.pavelkv96.vk_coffee.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView mFirstNameTV;
    public TextView mLastNameTV;

    public MyViewHolder(View itemView) {
        super(itemView);
        mFirstNameTV = itemView.findViewById(R.id.first_name_text_view);
        mLastNameTV = itemView.findViewById(R.id.last_name_text_view);
    }
}
