package com.github.pavelkv96.vk_coffee.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.pavelkv96.vk_coffee.R;

public class MyFriendsViewHolder extends RecyclerView.ViewHolder {

    public TextView name_friend_text;
    public ImageView profile_avatar_friend_image_view_text;

    public MyFriendsViewHolder(View v) {
        super(v);
        name_friend_text= v.findViewById(R.id.name_friend_text_view);
        profile_avatar_friend_image_view_text = v.findViewById(R.id.profile_avatar_friend_image_view);
    }
}
