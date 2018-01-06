package com.github.pavelkv96.vksdk_library.streams;

import java.io.IOException;
import java.io.InputStream;

public interface IHttpClient<T> {
    InputStream get(T path) throws IOException;
}
