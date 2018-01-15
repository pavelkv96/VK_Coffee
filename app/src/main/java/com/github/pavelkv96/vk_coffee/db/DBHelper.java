package com.github.pavelkv96.vk_coffee.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IChatsTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IDialogsView;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IFriendsTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IMessagesTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IMessagesTopIdsTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IMessagesUnreadCountersTable;

public class DBHelper extends SQLiteOpenHelper {
    private static final Integer VERSION = 1;
    private static final String NAME_DB = "vk.db";

    public DBHelper(Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(IFriendsTable.CREATE_TABLE_FRIENDS);
        db.execSQL(IChatsTable.CREATE_TABLE_CHATS);
        db.execSQL(IMessagesTable.CREATE_TABLE_MESSAGES);
        db.execSQL(IMessagesUnreadCountersTable.CREATE_TABLE_MESSAGES_UNREAD_COUNTERS);
        db.execSQL(IMessagesTopIdsTable.CREATE_TABLE_MESSAGES_TOP_IDS);
        db.execSQL(IDialogsView.CREATE_VIEW_DIALOGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
