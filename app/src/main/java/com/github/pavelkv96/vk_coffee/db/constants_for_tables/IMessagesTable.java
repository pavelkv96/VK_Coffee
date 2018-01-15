package com.github.pavelkv96.vk_coffee.db.constants_for_tables;

import android.net.Uri;

public interface IMessagesTable extends IProvider {

    String TABLE_MESSAGES = "messages";

    Uri URI_MESSAGES = Uri.withAppendedPath(BASE_URI, TABLE_MESSAGES);

    String _MID = "_mid";
    String PEER = "peer";
    String SENDER = "sender";
    String TEXT = "text";
    String TIME = "time";
    String FWD = "fwd_messages";

    String CREATE_TABLE_MESSAGES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_MESSAGES + " ( " +
                    _MID + " INTEGER unique, " +
                    PEER + " INTEGER NOT NULL, " +
                    SENDER + " INTEGER NOT NULL, " +
                    TEXT + " TEXT, " +
                    TIME + " INTEGER NOT NULL, " +
                    FWD + " TEXT)";
}
