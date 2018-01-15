package com.github.pavelkv96.vk_coffee.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.pavelkv96.vk_coffee.R;

public class MyDialogsViewHolder extends RecyclerView.ViewHolder {

    public TextView name_friend_text_view;
    public TextView message_text_view;
    public TextView time_message_text_view;

    public MyDialogsViewHolder(View v) {
        super(v);
        name_friend_text_view = v.findViewById(R.id.name_friend_text_view);
        message_text_view = v.findViewById(R.id.message_text_view);
        time_message_text_view = v.findViewById(R.id.time_message_text_view);
    }
}
