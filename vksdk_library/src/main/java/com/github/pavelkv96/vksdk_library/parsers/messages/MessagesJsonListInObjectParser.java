package com.github.pavelkv96.vksdk_library.parsers.messages;

import com.github.pavelkv96.vksdk_library.model.messages.IMessageListParser;
import com.github.pavelkv96.vksdk_library.model.messages.IVKApiMessagesList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessagesJsonListInObjectParser implements IMessageListParser {
    private static final String RESPONSE_OBJECT = "response";
    private static final String USER_ARRAY = "items";
    private final String mString;

    public MessagesJsonListInObjectParser(String pSource) {
        mString = pSource;
    }

    @Override
    public IVKApiMessagesList parse() throws Exception {
        final JSONObject rootObject = new JSONObject(mString);

        JSONObject response = null;

        try {
            response = rootObject.getJSONObject(RESPONSE_OBJECT);
        } catch (final JSONException e) {
            e.getMessage();
        }
        assert response != null;
        final JSONArray jsonArray = response.optJSONArray(USER_ARRAY);

        return new MessageListWrapper(jsonArray);
    }
}
