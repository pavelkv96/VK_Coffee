package com.github.pavelkv96.vksdk_library.parsers.users;

import com.github.pavelkv96.vksdk_library.model.users.IUsers;
import com.github.pavelkv96.vksdk_library.model.users.IUsersList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsersListWrapper implements IUsersList {
    private final JSONArray mJsonArray;

    public UsersListWrapper(JSONArray mJsonArray) {
        this.mJsonArray = mJsonArray;
    }

    @Override
    public List<IUsers> getUsers() {
        final List<UserJsonWrapper> messageJsonWrappers = new ArrayList<>();
        for (int i = 0; i < mJsonArray.length(); i++) {
            try {
                final JSONObject jsonObject = mJsonArray.getJSONObject(i);
                final UserJsonWrapper messageJsonWrapper = new UserJsonWrapper(jsonObject);
                messageJsonWrappers.add(messageJsonWrapper);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        List<IUsers> iAlls = new ArrayList<>();
        iAlls.addAll(messageJsonWrappers);
        return iAlls;
    }
}
