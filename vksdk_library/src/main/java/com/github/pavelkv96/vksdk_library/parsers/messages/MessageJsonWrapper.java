package com.github.pavelkv96.vksdk_library.parsers.messages;

import com.github.pavelkv96.vksdk_library.model.messages.VKApiMessage;

import org.json.JSONObject;

public class MessageJsonWrapper implements VKApiMessage {
    private static final String FIELD_ID = "id";
    private static final String FIELD_BODY = "body";
    private static final String FIELD_USER_ID = "user_id";
    private static final String FIELD_DATE = "date";
    private static final String FIELD_READ_STATE = "read_state";
    private static final String FIELD_OUT = "out";
    //private static final String FIELD_RANDOM_ID = "random_id";
    //private static final String FIELD_ATTACHMENTS = "attachments";
    private static final String FIELD_FWD_MESSAGES = "fwd_messages";
    //private static final String FIELD_TITLE = "title";
    private static final String FIELD_CHAT_ID = "chat_id";

    private final JSONObject mJsonObject;

    public MessageJsonWrapper(JSONObject pJsonObject) {
        this.mJsonObject = pJsonObject;
    }

    @Override
    public int id() {
        return mJsonObject.optInt(FIELD_ID);
    }

    @Override
    public int user_id() {
        return mJsonObject.optInt(FIELD_USER_ID);
    }

    @Override
    public long date() {
        return mJsonObject.optLong(FIELD_DATE);
    }

    @Override
    public boolean read_state() {
        return mJsonObject.optInt(FIELD_READ_STATE) == 1;
    }

    @Override
    public boolean out() {
        return mJsonObject.optInt(FIELD_OUT) == 1;
    }

    @Override
    public String body() {
        return mJsonObject.optString(FIELD_BODY);
    }

    @Override
    public String fwd_messages() {
        return mJsonObject.optString(FIELD_FWD_MESSAGES);
    }

    @Override
    public int chat_id() {
        return mJsonObject.optInt(FIELD_CHAT_ID);
    }
}
