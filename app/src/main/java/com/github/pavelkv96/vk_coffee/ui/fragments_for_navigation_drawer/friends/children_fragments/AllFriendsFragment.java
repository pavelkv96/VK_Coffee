package com.github.pavelkv96.vk_coffee.ui.fragments_for_navigation_drawer.friends.children_fragments;

import android.content.ContentValues;
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
import com.github.pavelkv96.vk_coffee.ui.Utils;
import com.github.pavelkv96.vk_coffee.ui.adapters.MyFriendsAdapter;
import com.github.pavelkv96.vksdk_library.model.users.IUsers;
import com.github.pavelkv96.vksdk_library.model.users.IUsersList;
import com.github.pavelkv96.vksdk_library.parsers.ParserFactory;

import java.util.ArrayList;
import java.util.List;

public class AllFriendsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private MyFriendsAdapter myFriendsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_friends, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.all_friends_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        //-------------------------------------------------------------
        List<IUsers> list = new ArrayList<>();
        String s = Utils.readFromFileUsers();
        IUsersList iUsersList = null;
        try {
            iUsersList = new ParserFactory().createParserJsonListInObject2(s).parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert iUsersList != null;
        for (IUsers iUsers : iUsersList.getUsers()) {
            list.add(iUsers);
        }
        //-------------------------------------------------------------
        fillTestElements(list);

        //-------------------------------------------------------------
        myFriendsAdapter = new MyFriendsAdapter();
        recyclerView.setAdapter(myFriendsAdapter);
        getLoaderManager().initLoader(0, null, this);
        return view;
    }

    private void fillTestElements(List<IUsers> users) {
        ContentValues[] cvArray = new ContentValues[users.size()];

        for (int i = 0; i < cvArray.length; i++) {
            ContentValues cv = new ContentValues();

            cv.put(IFriendsTable._UID, users.get(i).id());
            cv.put(IFriendsTable.FIRST_NAME, users.get(i).first_name());
            cv.put(IFriendsTable.LAST_NAME, users.get(i).last_name());
            cv.put(IFriendsTable.PHOTO_SMALL, users.get(i).photo_50());
            cv.put(IFriendsTable.IS_FRIEND, users.get(i).is_friend());
            cv.put(IFriendsTable.ONLINE, users.get(i).online());
            cv.put(IFriendsTable.LAST_SEEN_TIME, users.get(i).last_seen().time());
            cv.put(IFriendsTable.LAST_SEEN_PLATFORM, users.get(i).last_seen().platform());

            cvArray[i] = cv;
        }
        getActivity().getContentResolver().bulkInsert(IFriendsTable.URI_FRIENDS, cvArray);
    }

    @Override
    public String toString() {
        return "All";
        //return getContext().getResources().getString(R.string.all);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), IFriendsTable.URI_FRIENDS, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        myFriendsAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        myFriendsAdapter.release();
    }
}
