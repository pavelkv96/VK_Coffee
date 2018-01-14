package com.github.pavelkv96.vk_coffee.db.v8.constants_for_tables;

import android.net.Uri;

public interface IFriendsTable extends IProvider {

    String TABLE_FRIENDS = "friends";

    Uri URI_FRIENDS = Uri.withAppendedPath(BASE_URI, TABLE_FRIENDS);

    String _UID = "_uid";
    String FIRST_NAME = "first_name";
    String LAST_NAME = "last_name";
    String PHOTO_SMALL = "photo_small";
    String IS_FRIEND = "is_friend";
    String ONLINE = "online";
    String LAST_SEEN_TIME = "last_seen_time";
    String LAST_SEEN_PLATFORM = "last_seen_platform";

    String CREATE_TABLE_FRIENDS =
            "CREATE TABLE IF NOT EXISTS "+ TABLE_FRIENDS +" ("+
                    _UID +" INTEGER NOT NULL, " +
                    FIRST_NAME +" VARCHAR(150), " +
                    LAST_NAME +" VARCHAR(150), " +
                    PHOTO_SMALL +" VARCHAR(200), " +
                    IS_FRIEND +" BOOL, " +
                    ONLINE +" BOOL, " +
                    LAST_SEEN_TIME +" INTEGER, " +
                    LAST_SEEN_PLATFORM +" INTEGER)";

}
