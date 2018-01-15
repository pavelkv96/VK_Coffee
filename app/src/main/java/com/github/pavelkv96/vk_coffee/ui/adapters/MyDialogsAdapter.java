package com.github.pavelkv96.vk_coffee.ui.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pavelkv96.vk_coffee.R;
import com.github.pavelkv96.vk_coffee.db.constants_for_tables.IDialogsView;
import com.github.pavelkv96.vk_coffee.ui.holders.MyDialogsViewHolder;

public class MyDialogsAdapter extends RecyclerView.Adapter<MyDialogsViewHolder> {
    private Cursor mCursor;

    @Override
    public MyDialogsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialogs_item, parent, false);
        return new MyDialogsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyDialogsViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        String name_friend_text = mCursor.getString(
                mCursor.getColumnIndex(IDialogsView.FIRST_NAME) + mCursor.getColumnIndex(IDialogsView.LAST_NAME)
        );
        String message_text_ = mCursor.getString(mCursor.getColumnIndex(IDialogsView.TEXT));
        String time_message_text_ = mCursor.getString(mCursor.getColumnIndex(IDialogsView.TIME));

        holder.name_friend_text_view.setText(name_friend_text);
        holder.message_text_view.setText(message_text_);
        holder.time_message_text_view.setText(time_message_text_);
    }

    @Override
    public int getItemCount() {
        return (mCursor != null) ? mCursor.getCount() : 0;
    }

    @Override
    public long getItemId(int position) {
        if (mCursor != null) {
            if (mCursor.moveToPosition(position)) {
                int idc_id = mCursor.getColumnIndex(IDialogsView._MID);
                return mCursor.getLong(idc_id);
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
