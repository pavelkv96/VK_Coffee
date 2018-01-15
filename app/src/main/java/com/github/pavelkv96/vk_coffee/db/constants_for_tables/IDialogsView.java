package com.github.pavelkv96.vk_coffee.db.constants_for_tables;

import android.net.Uri;

public interface IDialogsView extends IMessagesTable, IFriendsTable, IChatsTable, IMessagesUnreadCountersTable, IMessagesTopIdsTable {

    String VIEW_DIALOGS = "dialogs";

    Uri URI_DIALOGS = Uri.withAppendedPath(BASE_URI, VIEW_DIALOGS);

    String CREATE_VIEW_DIALOGS =
            "CREATE VIEW " + VIEW_DIALOGS + " AS SELECT " +
                    TABLE_MESSAGES + ".*, " +
                    TABLE_FRIENDS + ".*, " +
                    TABLE_CHATS + ".*, " +
                    TABLE_MESSAGES_UNREAD_COUNTERS + "." + UNREAD_COUNT + ", " +
                    TABLE_MESSAGES_TOP_IDS + "." + MESSAGE_TIME +
                    " FROM " +
                    TABLE_MESSAGES_TOP_IDS + " JOIN " + TABLE_MESSAGES + " ON " + TABLE_MESSAGES_TOP_IDS + "." + MESSAGE_ID + "=" + TABLE_MESSAGES + "." + _MID +
                    " LEFT JOIN " + TABLE_FRIENDS + " ON " + TABLE_MESSAGES + "." + PEER + "=" + _UID +
                    " LEFT JOIN " + TABLE_CHATS + " ON " + TABLE_MESSAGES + "." + PEER + "-2000000000" + "=" + _CHAT_ID +
                    " LEFT JOIN " + TABLE_MESSAGES_UNREAD_COUNTERS + " ON " + TABLE_MESSAGES + "." + PEER + "=" + TABLE_MESSAGES_UNREAD_COUNTERS + "." + IMessagesUnreadCountersTable._PEER;
}
