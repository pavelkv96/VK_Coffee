package com.github.pavelkv96.vk_coffee.db.v8.constants_for_tables;

import android.net.Uri;

public interface IChatsTable extends IProvider {

    String TABLE_CHATS = "chats";

    Uri URI_CHATS = Uri.withAppendedPath(BASE_URI, TABLE_CHATS);

    String _CHAT_ID = "_chat_id";
    String TITLE = "title";
    String ADMIN = "admin";
    String PHOTO = "photo";

    String CREATE_TABLE_CHATS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_CHATS + " (" +
                    _CHAT_ID + " INTEGER, " +
                    TITLE + " VARCHAR(500), " +
                    ADMIN + " INTEGER NOT NULL, " +
                    PHOTO + " VARCHAR(500))";
}
