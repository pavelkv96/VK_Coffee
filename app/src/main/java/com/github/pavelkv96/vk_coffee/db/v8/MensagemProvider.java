package com.github.pavelkv96.vk_coffee.db.v8;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.github.pavelkv96.vk_coffee.db.v8.constants_for_tables.IFriendsTable;
import com.github.pavelkv96.vk_coffee.db.v8.constants_for_tables.IMessagesTable;

public class MensagemProvider extends ContentProvider {

    public static final int FRIENDS = 1;
    public static final int FRIENDS_POR_ID = 2;
    public static final int MESSAGES = 3;
    public static final int MESSAGES_POR_ID = 4;

    UriMatcher mUriMatcher;
    DBHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(IMessagesTable.AUTHORITY, "friends/", FRIENDS);
        mUriMatcher.addURI(IMessagesTable.AUTHORITY, "friends/#", FRIENDS_POR_ID);
        mUriMatcher.addURI(IMessagesTable.AUTHORITY, "messages", MESSAGES);
        mUriMatcher.addURI(IMessagesTable.AUTHORITY, "messages/#", MESSAGES_POR_ID);
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
        switch (mUriMatcher.match(uri)) {
            case MESSAGES: {
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                Cursor cursor = db.query(IMessagesTable.TABLE_MESSAGES, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case MESSAGES_POR_ID: {
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                Cursor cursor = db.query(IMessagesTable.TABLE_MESSAGES,
                        projection, IMessagesTable._ID + " = ?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case FRIENDS: {
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                Cursor cursor = db.query(IFriendsTable.TABLE_FRIENDS, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case FRIENDS_POR_ID: {
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                Cursor cursor = db.query(IFriendsTable.TABLE_FRIENDS,
                        projection, IMessagesTable._ID + " = ?", new String[]{uri.getLastPathSegment()}, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        if (mUriMatcher.match(uri) == FRIENDS) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            long id = db.insert(IMessagesTable.TABLE_MESSAGES, null, contentValues);
            Uri insertUri = Uri.withAppendedPath(IMessagesTable.BASE_URI, String.valueOf(id));
            db.close();
            notifyChanges(uri);
            return insertUri;
        } else {
            throw new UnsupportedOperationException("Não suportada");
        }
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        if (mUriMatcher.match(uri) == FRIENDS_POR_ID) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            int linhasAfetadas = db.delete(IMessagesTable.TABLE_MESSAGES, IMessagesTable._ID + " = ?", new String[]{uri.getLastPathSegment()});
            db.close();
            Log.e("myLogs", uri.getLastPathSegment());
            notifyChanges(uri);
            return linhasAfetadas;

        } else {
            throw new UnsupportedOperationException("Uri inválida para exclusão.");
        }
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (mUriMatcher.match(uri) == FRIENDS_POR_ID) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            int linhasAfetadas = db.update(IMessagesTable.TABLE_MESSAGES,
                    values, IMessagesTable._ID + " = ?", new String[]{uri.getLastPathSegment()});
            db.close();
            notifyChanges(uri);
            return linhasAfetadas;

        } else {
            throw new UnsupportedOperationException("Uri inválida para atualização.");
        }
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] contentValues) {
        switch (mUriMatcher.match(uri)) {
            case FRIENDS: {
                if (mUriMatcher.match(uri) == FRIENDS) {
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    long id = 0;
                    for (ContentValues contentValues1 : contentValues) {
                        id = db.insert(IFriendsTable.TABLE_FRIENDS, null, contentValues1);
                        Uri.withAppendedPath(IMessagesTable.BASE_URI, String.valueOf(id));
                        id++;
                    }
                    db.close();
                    notifyChanges(uri);
                    return (int) id;
                } else {
                    throw new UnsupportedOperationException("Não suportada");
                }
            }
            //break;

            case MESSAGES: {
                if (mUriMatcher.match(uri) == MESSAGES) {
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    long id = 0;
                    for (ContentValues contentValues1 : contentValues) {
                        id = db.insert(IMessagesTable.TABLE_MESSAGES, null, contentValues1);
                        Uri.withAppendedPath(IMessagesTable.BASE_URI, String.valueOf(id));
                        id++;
                    }
                    db.close();
                    notifyChanges(uri);
                    return (int) id;
                } else {
                    throw new UnsupportedOperationException("Não suportada");
                }
            }
            //break;
            default:
                return -11111111;
        }
    }

    private void notifyChanges(Uri uri) {
        if (getContext() != null && getContext().getContentResolver() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }
}
