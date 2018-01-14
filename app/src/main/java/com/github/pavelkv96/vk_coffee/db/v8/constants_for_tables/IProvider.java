package com.github.pavelkv96.vk_coffee.db.v8.constants_for_tables;

import android.net.Uri;

import com.github.pavelkv96.vk_coffee.BuildConfig;

interface IProvider {
    String AUTHORITY = BuildConfig.APPLICATION_ID + ".db.v8.MensagemProvider";
    Uri BASE_URI = Uri.parse("content://"+ AUTHORITY);
}
