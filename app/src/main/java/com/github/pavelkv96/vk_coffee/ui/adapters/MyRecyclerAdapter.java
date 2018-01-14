package com.github.pavelkv96.vk_coffee.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pavelkv96.vk_coffee.R;
import com.github.pavelkv96.vk_coffee.ui.Friends;
import com.github.pavelkv96.vk_coffee.ui.holders.MyViewHolder;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    ArrayList<Friends> friends;

    public MyRecyclerAdapter(Context context, ArrayList<Friends> friends) {
        this.context = context;
        this.friends = friends;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mFirstNameTV.setText(friends.get(position).getFirstName());
        holder.mLastNameTV.setText(friends.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
