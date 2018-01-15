package com.github.pavelkv96.vk_coffee.ui.fragments_for_navigation_drawer;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
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
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IChatsTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IDialogsView;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IMessagesTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IMessagesTopIdsTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IMessagesUnreadCountersTable;
import com.github.pavelkv96.vk_coffee.ui.Utils;
import com.github.pavelkv96.vk_coffee.ui.adapters.MyDialogsAdapter;
import com.github.pavelkv96.vksdk_library.model.dialogs.IDialogItem;
import com.github.pavelkv96.vksdk_library.model.dialogs.IDialogsList;
import com.github.pavelkv96.vksdk_library.parsers.ParserFactory;

import java.util.ArrayList;
import java.util.List;

public class DialogsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private MyDialogsAdapter myDialogsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialogs, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.dialogs_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        //-------------------------------------------------------------
        List<IDialogItem> dialogItemList = new ArrayList<>();
        String s = Utils.readFromFileDialogs();
        IDialogsList iDialogsList = null;
        try {
            iDialogsList = new ParserFactory().createParserJsonListInObject1(s).parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert iDialogsList != null;
        for (IDialogItem iDialogItem : iDialogsList.getListDialogs()) {
            dialogItemList.add(iDialogItem);
        }
        //-------------------------------------------------------------
        fillTestElements(dialogItemList);
        //-------------------------------------------------------------
        myDialogsAdapter = new MyDialogsAdapter();
        recyclerView.setAdapter(myDialogsAdapter);

        getLoaderManager().initLoader(0, null, this);

        return view;
    }

    private void fillTestElements(List<IDialogItem> dialogItems) {
        int listSize = dialogItems.size();

        ContentValues[] cvArray = new ContentValues[listSize];
        ContentValues[] cvArray1 = new ContentValues[listSize];
        ContentValues[] cvArray2 = new ContentValues[listSize];
        ContentValues[] cvArray3 = new ContentValues[listSize];

        for (int i = 0; i < cvArray.length; i++) {
            ContentValues cv = new ContentValues();
            ContentValues cv1 = new ContentValues();
            ContentValues cv2 = new ContentValues();
            ContentValues cv3 = new ContentValues();

            cv.put(IMessagesTable._MID, dialogItems.get(i).idialog().id());
            cv.put(IMessagesTable.PEER, dialogItems.get(i).idialog().chat_id() != 0
                    ? 2000000000 +dialogItems.get(i).idialog().chat_id()
                    : dialogItems.get(i).idialog().user_id()
            );
            cv.put(IMessagesTable.SENDER, dialogItems.get(i).idialog().user_id());
            cv.put(IMessagesTable.TEXT, dialogItems.get(i).idialog().body());
            cv.put(IMessagesTable.TIME, dialogItems.get(i).idialog().date());
            cv.put(IMessagesTable.FWD, dialogItems.get(i).idialog().fwd_messages());

            //if (dialogItems.get(i).idialog().chat_id() != 0) {
            cv1.put(IChatsTable._CHAT_ID, 2000000000 + dialogItems.get(i).idialog().chat_id());
            cv1.put(IChatsTable.TITLE, dialogItems.get(i).idialog().title());
            cv1.put(IChatsTable.ADMIN, dialogItems.get(i).idialog().admin_id());
            cv1.put(IChatsTable.PHOTO, dialogItems.get(i).idialog().photo_50());
            //}

            cv2.put(IMessagesUnreadCountersTable._PEER, dialogItems.get(i).idialog().chat_id() != 0
                    ? 2000000000 + dialogItems.get(i).idialog().chat_id()
                    : dialogItems.get(i).idialog().user_id()
            );
            cv2.put(IMessagesUnreadCountersTable.UNREAD_COUNT, dialogItems.get(i).unread());

            cv3.put(IMessagesTopIdsTable._PEER, dialogItems.get(i).idialog().chat_id() != 0
                    ? 2000000000 + dialogItems.get(i).idialog().chat_id()
                    : dialogItems.get(i).idialog().user_id());
            cv3.put(IMessagesTopIdsTable.MESSAGE_ID, dialogItems.get(i).idialog().id());
            cv3.put(IMessagesTopIdsTable.MESSAGE_TIME, dialogItems.get(i).idialog().date());

            cvArray[i] = cv;
            cvArray1[i] = cv1;
            cvArray2[i] = cv2;
            cvArray3[i] = cv3;
            Log.e("myLogs", i + "");
        }

        getActivity().getContentResolver().bulkInsert(IMessagesTable.URI_MESSAGES, cvArray);
        getActivity().getContentResolver().bulkInsert(IChatsTable.URI_CHATS, cvArray1);
        getActivity().getContentResolver().bulkInsert(IMessagesUnreadCountersTable.URI_MESSAGES_UNREAD_COUNTERS, cvArray2);
        getActivity().getContentResolver().bulkInsert(IMessagesTopIdsTable.URI_MESSAGES_TOP_IDS, cvArray3);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), IDialogsView.URI_DIALOGS, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        myDialogsAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        myDialogsAdapter.release();
    }
}
