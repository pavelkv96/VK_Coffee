package com.github.pavelkv96.vk_coffee.ui.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pavelkv96.vk_coffee.R;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IFriendsTable;
import com.github.pavelkv96.vk_coffee.ui.holders.MyFriendsViewHolder;

public class MyFriendsAdapter extends RecyclerView.Adapter<MyFriendsViewHolder> {
    private Cursor mCursor;

    @Override
    public MyFriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_item, parent, false);
        return new MyFriendsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyFriendsViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        String name_friend_text = mCursor.getString(
                mCursor.getColumnIndex(IFriendsTable.FIRST_NAME) + mCursor.getColumnIndex(IFriendsTable.LAST_NAME)
        );
        String profile_avatar_friend_image = mCursor.getString(mCursor.getColumnIndex(IFriendsTable.PHOTO_SMALL));

        holder.name_friend_text.setText(name_friend_text);
        holder.profile_avatar_friend_image_view_text.setImageResource(/*profile_avatar_friend_image*/R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return (mCursor != null) ? mCursor.getCount() : 0;
    }

    @Override
    public long getItemId(int position) {
        if (mCursor != null) {
            if (mCursor.moveToPosition(position)) {
                int uid = mCursor.getColumnIndex(IFriendsTable._UID);
                return mCursor.getLong(uid);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void setCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    public void release() {
        if (mCursor != null) {
            mCursor = null;
        }
    }
}
