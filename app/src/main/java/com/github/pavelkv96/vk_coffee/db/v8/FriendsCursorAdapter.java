package com.github.pavelkv96.vk_coffee.db.v8;


import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pavelkv96.vk_coffee.R;
import com.github.pavelkv96.vk_coffee.db.v8.constants_for_tables.IMessagesTable;

public class FriendsCursorAdapter extends RecyclerView.Adapter<VH> {
    private Cursor mCursor;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mensagem, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        mCursor.moveToPosition(position);
        int idx_titulo = mCursor.getColumnIndex(IMessagesTable.NAME);
        int idx_descr = mCursor.getColumnIndex(IMessagesTable.TIME);

        String titulo = mCursor.getString(idx_titulo);
        String descricao = mCursor.getString(idx_descr);

        holder.mText1.setText(titulo);
        holder.mText2.setText(descricao);
    }

    @Override
    public int getItemCount() {
        return (mCursor != null) ? mCursor.getCount() : 0;
    }

    @Override
    public long getItemId(int position) {
        if (mCursor != null) {
            if (mCursor.moveToPosition(position)) {
                int idx_id = mCursor.getColumnIndex(IMessagesTable._ID);
                return mCursor.getLong(idx_id);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void setCursor(Cursor newCursor) {
        mCursor = newCursor;
        //Log.e("myLogs", "setCursor: "+mCursor.getCount());
        notifyDataSetChanged();
    }

    public void release() {
        if (mCursor != null) {
            mCursor = null;
        }
    }
}
