package com.github.pavelkv96.vk_coffee.ui.fragments.friends.children_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pavelkv96.vk_coffee.R;
import com.github.pavelkv96.vk_coffee.ui.Friends;
import com.github.pavelkv96.vk_coffee.ui.adapters.MyRecyclerAdapter;

import java.util.ArrayList;

public class OnlineFriendsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_online_friends, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.online_friends_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new MyRecyclerAdapter(this.getActivity(), getFriendsList()));
        return view;
    }

    public ArrayList<Friends> getFriendsList() {
        ArrayList<Friends> friends = new ArrayList<>();
        friends.add(new Friends("Jake", "Lanning"));
        friends.add(new Friends("Margert", "Buford"));
        friends.add(new Friends("Inga", "Carbin"));
        friends.add(new Friends("Maegan", "Tumlin"));
        friends.add(new Friends("Vernia", "Sligh"));
        friends.add(new Friends("Kimbery", "Styer"));
        friends.add(new Friends("Waldo", "Clute"));
        friends.add(new Friends("Karren", "Nicols"));
        friends.add(new Friends("Shaunda", "Chaudhry"));
        friends.add(new Friends("Saul", "Girouard"));
        friends.add(new Friends("Lily", "Coltrane"));
        friends.add(new Friends("Willian", "Honda"));
        friends.add(new Friends("Antonia", "Holmquist"));
        friends.add(new Friends("Jules", "Austell"));
        friends.add(new Friends("Ema", "Cantley"));
        friends.add(new Friends("Harriet", "Birch"));
        friends.add(new Friends("Morgan", "Haith"));
        friends.add(new Friends("Landon", "Evitt"));
        return friends;
    }

    @Override
    public String toString() {
        return "Online";
    }
}
