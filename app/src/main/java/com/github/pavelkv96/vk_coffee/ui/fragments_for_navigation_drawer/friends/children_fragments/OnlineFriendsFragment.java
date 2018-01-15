package com.github.pavelkv96.vk_coffee.ui.fragments_for_navigation_drawer.friends.children_fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pavelkv96.vk_coffee.R;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IFriendsTable;
import com.github.pavelkv96.vk_coffee.ui.adapters.MyFriendsAdapter;

public class OnlineFriendsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private MyFriendsAdapter myRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_online_friends, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.online_friends_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        myRecyclerAdapter = new MyFriendsAdapter();

        //---------------------------------------------------------
        getLoaderManager().initLoader(0, null, this);
        //---------------------------------------------------------

        recyclerView.setAdapter(myRecyclerAdapter);
        return view;
    }

    @Override
    public String toString() {
        return "Online";
        //return getContext().getResources().getString(R.string.online);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                IFriendsTable.URI_FRIENDS,
                null,
                IFriendsTable.IS_FRIEND + "=? and " + IFriendsTable.ONLINE + "=?",
                new String[]{"1", "1"},
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        myRecyclerAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        myRecyclerAdapter.release();
    }
}
