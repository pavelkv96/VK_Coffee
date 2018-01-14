package com.github.pavelkv96.vksdk_library.parsers.dialogs;

import com.github.pavelkv96.vksdk_library.model.dialogs.IDialog;
import com.github.pavelkv96.vksdk_library.parsers.messages.MessageJsonWrapper;

import org.json.JSONObject;

public class DialogJsonWrapper extends MessageJsonWrapper implements IDialog {
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_ADMIN_ID = "admin_id";
    private static final String FIELD_USERS_COUNT = "users_count";
    private static final String FIELD_PHOTO_50 = "photo_50";

    private final JSONObject mJsonObject;

    public DialogJsonWrapper(JSONObject pJsonObject) {
        super(pJsonObject);
        this.mJsonObject = pJsonObject;
    }

    @Override
    public String title() {
        return mJsonObject.optString(FIELD_TITLE);
    }

    @Override
    public int users_count() {
        return mJsonObject.optInt(FIELD_USERS_COUNT);
    }

    @Override
    public long admin_id() {
        return mJsonObject.optLong(FIELD_ADMIN_ID);
    }

    @Override
    public String photo_50() {
        return mJsonObject.optString(FIELD_PHOTO_50);
    }
}
