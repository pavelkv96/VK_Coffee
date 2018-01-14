package com.github.pavelkv96.vksdk_library.parsers.dialogs;

import com.github.pavelkv96.vksdk_library.model.dialogs.IDialog;
import com.github.pavelkv96.vksdk_library.model.dialogs.IDialogItem;

import org.json.JSONObject;

public class DialogJsonWrapperItem implements IDialogItem {
    private final JSONObject mJsonArray;

    private static final String FIELD_UNREAD = "unread";
    private static final String FIELD_MESSAGE = "message";
    private static final String FIELD_IN_READ = "in_read";
    private static final String FIELD_OUT_READ = "out_read";

    public DialogJsonWrapperItem(JSONObject pSource) {
        this.mJsonArray = pSource;
    }

    @Override
    public int unread() {
        return mJsonArray.optInt(FIELD_UNREAD);
    }

    @Override
    public IDialog idialog() {
        final JSONObject jsonObject = mJsonArray.optJSONObject(FIELD_MESSAGE);
        return new DialogJsonWrapper(jsonObject);
    }

    @Override
    public long in_read() {
        return mJsonArray.optLong(FIELD_IN_READ);
    }

    @Override
    public long out_read() {
        return mJsonArray.optLong(FIELD_OUT_READ);
    }
}
