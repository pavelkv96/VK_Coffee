package com.github.pavelkv96.vksdk_library.parsers.users;

import com.github.pavelkv96.vksdk_library.model.users.IUsersList;
import com.github.pavelkv96.vksdk_library.model.users.IUsersListParser;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsersJsonListInObjectParser implements IUsersListParser {
    private static final String RESPONSE_ARRAY_OBJECT = "response";
    private final String mString;

    public UsersJsonListInObjectParser(String pSource) {
        mString = pSource;
    }

    @Override
    public IUsersList parse() throws Exception {
        final JSONObject rootObject = new JSONObject(mString);

        JSONArray response;

        response = rootObject.optJSONArray(RESPONSE_ARRAY_OBJECT);

        return new UsersListWrapper(response);
    }
}
