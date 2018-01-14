package com.github.pavelkv96.vk_coffee.db.v8;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.pavelkv96.vk_coffee.R;

.R;

public class VH extends RecyclerView.ViewHolder {
    public TextView mText1;
    public TextView mText2;

    public VH(View v) {
        super(v);
        mText1 = v.findViewById(R.id.text1);
        mText2 = v.findViewById(R.id.text2);
    }
}