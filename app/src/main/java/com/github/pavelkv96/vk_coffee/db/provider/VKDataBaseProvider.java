package com.github.pavelkv96.vk_coffee.db.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.pavelkv96.vk_coffee.db.DBHelper;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IChatsTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IDialogsView;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IFriendsTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IMessagesTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IMessagesTopIdsTable;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IMessagesUnreadCountersTable;

public class VKDataBaseProvider extends ContentProvider {

    public static final int FRIENDS = 1;
    public static final int FRIENDS_BY_ID = 2;
    public static final int MESSAGES = 3;
    public static final int MESSAGES_BY_ID = 4;
    public static final int MESSAGES_UNREAD_COUNT = 5;
    public static final int MESSAGES_UNREAD_COUNT_BY_ID = 6;
    public static final int CHATS = 7;
    public static final int CHATS_BY_ID = 8;
    public static final int MESSAGES_TOP_IDS = 9;
    public static final int MESSAGES_TOP_IDS_BY_ID = 10;
    public static final int DIALOGS = 11;
    public static final int DIALOGS_TOP_IDS_BY_ID = 12;

    UriMatcher mUriMatcher;
    DBHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(IFriendsTable.AUTHORITY, "friends/", FRIENDS);
        mUriMatcher.addURI(IFriendsTable.AUTHORITY, "friends/#", FRIENDS_BY_ID);
        mUriMatcher.addURI(IMessagesTable.AUTHORITY, "messages", MESSAGES);
        mUriMatcher.addURI(IMessagesTable.AUTHORITY, "messages/#", MESSAGES_BY_ID);
        mUriMatcher.addURI(IMessagesUnreadCountersTable.AUTHORITY, "messages_unread_counters/", MESSAGES_UNREAD_COUNT);
        mUriMatcher.addURI(IMessagesUnreadCountersTable.AUTHORITY, "messages_unread_counters/#", MESSAGES_UNREAD_COUNT_BY_ID);
        mUriMatcher.addURI(IChatsTable.AUTHORITY, "chats/", CHATS);
        mUriMatcher.addURI(IChatsTable.AUTHORITY, "chats/#", CHATS_BY_ID);
        mUriMatcher.addURI(IMessagesTopIdsTable.AUTHORITY, "messages_top_ids/", MESSAGES_TOP_IDS);
        mUriMatcher.addURI(IMessagesTopIdsTable.AUTHORITY, "messages_top_ids/#", MESSAGES_TOP_IDS_BY_ID);
        mUriMatcher.addURI(IDialogsView.AUTHORITY, "dialogs/", DIALOGS);
        mUriMatcher.addURI(IDialogsView.AUTHORITY, "dialogs/#", DIALOGS_TOP_IDS_BY_ID);
        mDbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = null;
        db.beginTransaction();
        try {
            switch (mUriMatcher.match(uri)) {
                case DIALOGS: {
                    cursor = db.query(IDialogsView.VIEW_DIALOGS, projection, selection, selectionArgs, null, null, sortOrder);
                }
                break;
                case DIALOGS_TOP_IDS_BY_ID: {
                    cursor = db.query(IDialogsView.VIEW_DIALOGS,
                            projection, IMessagesTable._MID + " = ?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
                }
                break;
                case MESSAGES: {
                    cursor = db.query(IDialogsView.VIEW_DIALOGS, projection, selection, selectionArgs, null, null, sortOrder);
                }
                break;
                case MESSAGES_BY_ID: {
                    cursor = db.query(IDialogsView.VIEW_DIALOGS,
                            projection, IMessagesTable._MID + " = ?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
                }
                break;
                case FRIENDS: {
                    cursor = db.query(IFriendsTable.TABLE_FRIENDS, projection, selection, selectionArgs, null, null, sortOrder);
                }
                break;
                case FRIENDS_BY_ID: {
                    cursor = db.query(IFriendsTable.TABLE_FRIENDS,
                            projection, IFriendsTable._UID + " = ?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
                }
                break;
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            db.endTransaction();
        }

        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] contentValues) {
        long id = -1;
        switch (mUriMatcher.match(uri)) {
            case FRIENDS: {
                id = bulkInsert(contentValues, IFriendsTable.BASE_URI, IFriendsTable.TABLE_FRIENDS);
            }
            break;
            case MESSAGES: {
                id = bulkInsert(contentValues, IMessagesTable.BASE_URI, IMessagesTable.TABLE_MESSAGES);
            }
            break;
            case CHATS: {
                id = bulkInsert(contentValues, IChatsTable.BASE_URI, IChatsTable.TABLE_CHATS);
            }
            break;
            case MESSAGES_UNREAD_COUNT: {
                id = bulkInsert(contentValues, IMessagesUnreadCountersTable.BASE_URI, IMessagesUnreadCountersTable.TABLE_MESSAGES_UNREAD_COUNTERS);
            }
            break;
            case MESSAGES_TOP_IDS: {
                id = bulkInsert(contentValues, IMessagesTopIdsTable.BASE_URI, IMessagesTopIdsTable.TABLE_MESSAGES_TOP_IDS);
            }
            break;
        }
        notifyChanges(uri);
        return (int) id;
    }

    private void notifyChanges(Uri uri) {
        if (getContext() != null && getContext().getContentResolver() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }

    private long bulkInsert(ContentValues[] contentValues, Uri baseUri, String tableName) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long id = 0;
        db.beginTransaction();
        try {
            for (ContentValues contentValues1 : contentValues) {
                id = db.insertWithOnConflict(tableName, null, contentValues1, SQLiteDatabase.CONFLICT_IGNORE);
                Uri.withAppendedPath(baseUri, String.valueOf(id));
                id++;
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            db.endTransaction();
        }
        return id;
    }
}
