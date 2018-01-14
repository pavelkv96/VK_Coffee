package com.github.pavelkv96.vk_coffee.db.v8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final Integer VERSION = 1;
    private static final String NAME_DB = "vk.db";

    public DBHelper(Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("CREATE TABLE IF NOT EXISTS "+ IMessagesTable.TABLE_MESSAGES +" ("+
                IMessagesTable._ID +" INTEGER NOT NULL, " +
                IMessagesTable.PEER +" INTEGER NOT NULL, " +
                IMessagesTable.TEXT +" TEXT, " +
                IMessagesTable.TIME +" INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ IFriendsTable.TABLE_FRIENDS +" ("+
                IMessagesTable._ID +" INTEGER NOT NULL, " +
                IMessagesTable.PEER +" INTEGER NOT NULL, " +
                IMessagesTable.NAME +" TEXT, " +
                IMessagesTable.TIME +" INTEGER NOT NULL)");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
