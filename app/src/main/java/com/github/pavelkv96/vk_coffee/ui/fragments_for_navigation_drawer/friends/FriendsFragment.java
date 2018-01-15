package com.github.pavelkv96.vk_coffee.ui.fragments_for_navigation_drawer.friends;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pavelkv96.vk_coffee.R;
import com.github.pavelkv96.vk_coffee.ui.adapters.SectionsPagerAdapter;
import com.github.pavelkv96.vk_coffee.ui.fragments_for_navigation_drawer.friends.children_fragments.AllFriendsFragment;
import com.github.pavelkv96.vk_coffee.ui.fragments_for_navigation_drawer.friends.children_fragments.OnlineFriendsFragment;

public class FriendsFragment extends Fragment {

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        mViewPager = view.findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        this.addPages();

        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    private void addPages(){
        mSectionsPagerAdapter.addPage(new AllFriendsFragment());
        mSectionsPagerAdapter.addPage(new OnlineFriendsFragment());
    }
}