package com.github.pavelkv96.vksdk_library.parsers.messages;

import com.github.pavelkv96.vksdk_library.model.messages.IVKApiMessagesList;
import com.github.pavelkv96.vksdk_library.model.messages.VKApiMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageListWrapper implements IVKApiMessagesList {
    private final JSONArray mJsonArray;

    public MessageListWrapper(JSONArray mJsonArray) {
        this.mJsonArray = mJsonArray;
    }

    @Override
    public List<VKApiMessage> getListMessages() {
        final List<MessageJsonWrapper> messageJsonWrappers = new ArrayList<>();
        for (int i = 0; i < mJsonArray.length(); i++) {
            try {
                final JSONObject jsonObject = mJsonArray.getJSONObject(i);
                //System.out.println(jsonObject);
                final MessageJsonWrapper messageJsonWrapper = new MessageJsonWrapper(jsonObject);
                messageJsonWrappers.add(messageJsonWrapper);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        List<VKApiMessage> iAlls = new ArrayList<>();
        iAlls.addAll(messageJsonWrappers);
        return iAlls;
    }
}
