package com.github.pavelkv96.vksdk_library.parsers.users;

import com.github.pavelkv96.vksdk_library.model.users.IUserLastSeen;
import com.github.pavelkv96.vksdk_library.model.users.IUsers;

import org.json.JSONObject;

public class UserJsonWrapper implements IUsers {
    private static final String FIELD_ID = "id";
    private static final String FIELD_FIRST_NAME = "first_name";
    private static final String FIELD_LAST_NAME = "last_name";
    private static final String FIELD_PHOTO_50 = "photo_50";
    private static final String FIELD_IS_FRIEND = "is_friend";
    private static final String FIELD_ONLINE = "online";
    private static final String FIELD_LAST_SEEN = "last_seen";
    private static final String FIELD_TIME = "time";
    private static final String FIELD_PLATFORM = "platform";

    private final JSONObject mJsonObject;

    public UserJsonWrapper(JSONObject pJsonObject) {
        this.mJsonObject = pJsonObject;
    }

    @Override
    public long id() {
        return mJsonObject.optLong(FIELD_ID);
    }

    @Override
    public String first_name() {
        return mJsonObject.optString(FIELD_FIRST_NAME);
    }

    @Override
    public String last_name() {
        return mJsonObject.optString(FIELD_LAST_NAME);
    }

    @Override
    public String photo_50() {
        return mJsonObject.optString(FIELD_PHOTO_50);
    }

    @Override
    public int is_friend() {
        return mJsonObject.optInt(FIELD_IS_FRIEND);
    }

    @Override
    public int online() {
        return mJsonObject.optInt(FIELD_ONLINE);
    }

    @Override
    public IUserLastSeen last_seen() {
        IUserLastSeen iUserLastSeen = new IUserLastSeen() {
            JSONObject jsonArray = mJsonObject.optJSONObject(FIELD_LAST_SEEN);

            @Override
            public long time() {
                return jsonArray != null ? jsonArray.optLong(FIELD_TIME) : -1;
            }

            @Override
            public int platform() {
                return jsonArray != null ? (int) jsonArray.optLong(FIELD_PLATFORM) : -1;
            }
        };
        return iUserLastSeen;
    }
}
