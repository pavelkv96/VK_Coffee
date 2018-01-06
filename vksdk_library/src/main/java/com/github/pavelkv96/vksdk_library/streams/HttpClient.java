package com.github.pavelkv96.vksdk_library.streams;

import android.text.format.DateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient<String> {
    private static final int READ_TIMEOUT = (int) (30 * DateUtils.SECOND_IN_MILLIS);
    private static final int CONNECT_TIMEOUT = (int) (10 * DateUtils.SECOND_IN_MILLIS);

    @Override
    public InputStream get(String path) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(path)).openConnection();
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);
        return connection.getInputStream();
    }
}
