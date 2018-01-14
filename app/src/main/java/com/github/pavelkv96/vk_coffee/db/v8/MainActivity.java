package com.github.pavelkv96.vk_coffee.db.v8;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.pavelkv96.vk_coffee.R;
import com.github.pavelkv96.vk_coffee.db.v8.constants_for_tables.IMessagesTable;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    RecyclerView mRecyclerView;
    private MessagesCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillTestElements();
        //mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MessagesCursorAdapter(/*null*/);
        mAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(0, null, this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fillTestElements();
            }
        }, 5000);
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
        getContentResolver().bulkInsert(/*IMessagesTable.URI_MESSAGES*/null, cvArray);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        //return new CursorLoader(this, IMessagesTable.URI_MESSAGES, null, null, null, null);
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mAdapter.setCursor(null);
    }
}
