package com.github.pavelkv96.vk_coffee.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pavelkv96.vk_coffee.R;
import com.github.pavelkv96.vk_coffee.db.v8.constants_for_tables.IMessagesTable;
import com.github.pavelkv96.vk_coffee.db.v8.MessagesCursorAdapter;

public class MessagesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    RecyclerView mRecyclerView;
    private MessagesCursorAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        //fillTestElements();

        mRecyclerView = view.findViewById(R.id.messages_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MessagesCursorAdapter();
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);

        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fillTestElements();
            }
        }, 5000);

        return view;
    }

    private void fillTestElements() {
        int size = 4;
        ContentValues[] cvArray = new ContentValues[size];
        for (int i = 0; i < cvArray.length; i++) {
            ContentValues cv = new ContentValues();
            cv.put(IMessagesTable._ID, (i));
            cv.put(IMessagesTable.TEXT, ("text " + i));
            cv.put(IMessagesTable.TIME, ("time " + i));
            cv.put(IMessagesTable.PEER, ("peer " + i));
            cvArray[i] = cv;
        }
        getActivity().getContentResolver().bulkInsert(IMessagesTable.URI_MESSAGES, cvArray);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), IMessagesTable.URI_MESSAGES, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.release();
        Log.e("myLogs", mAdapter.getItemCount() + "");
    }
}
