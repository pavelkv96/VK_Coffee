package com.github.pavelkv96.vk_coffee.db.v8.constants_for_tables;

import android.net.Uri;

public interface IMessagesTopIdsTable extends IProvider {

    String TABLE_MESSAGES_TOP_IDS = "messages_top_ids";

    Uri URI_MESSAGES_TOP_IDS = Uri.withAppendedPath(BASE_URI, TABLE_MESSAGES_TOP_IDS);

    String _PEER = "_peer";
    String MESSAGE_ID = "message_id";
    String MESSAGE_TIME = "message_time";

    String CREATE_TABLE_MESSAGES_TOP_IDS =
            "CREATE TABLE IF NOT EXISTS "+ TABLE_MESSAGES_TOP_IDS +" ("+
                    _PEER +" INTEGER, " +
                    MESSAGE_ID +" INTEGER NOT NULL, " +
                    MESSAGE_TIME +" INTEGER NOT NULL)";
}
