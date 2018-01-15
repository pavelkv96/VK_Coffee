package com.github.pavelkv96.vk_coffee.db.constants_for_tables;

import android.net.Uri;

public interface IMessagesUnreadCountersTable extends IProvider {
    String TABLE_MESSAGES_UNREAD_COUNTERS = "messages_unread_counters";

    Uri URI_MESSAGES_UNREAD_COUNTERS = Uri.withAppendedPath(BASE_URI, TABLE_MESSAGES_UNREAD_COUNTERS);

    String _PEER = "_peer";
    String UNREAD_COUNT = "unread_count";

    String CREATE_TABLE_MESSAGES_UNREAD_COUNTERS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_MESSAGES_UNREAD_COUNTERS + " ( " +
                    _PEER + " INTEGER unique, " +
                    UNREAD_COUNT + " INTEGER NOT NULL DEFAULT 0)";
}
