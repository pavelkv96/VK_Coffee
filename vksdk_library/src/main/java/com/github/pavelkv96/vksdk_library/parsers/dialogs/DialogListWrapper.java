package com.github.pavelkv96.vksdk_library.parsers.dialogs;

import com.github.pavelkv96.vksdk_library.model.dialogs.IDialogItem;
import com.github.pavelkv96.vksdk_library.model.dialogs.IDialogsList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DialogListWrapper implements IDialogsList/*implements IVKApiMessagesList*/ {
    private final JSONArray mJsonArray;

    public DialogListWrapper(JSONArray mJsonArray) {
        this.mJsonArray = mJsonArray;
    }

    @Override
    public List<IDialogItem> getListDialogs() {
        final List<IDialogItem> iDialogItems = new ArrayList<>();
        for (int i = 0; i < mJsonArray.length(); i++) {
            //try {
            final JSONObject jsonObject = mJsonArray.optJSONObject(i);
            //System.out.println(jsonObject);
            final DialogJsonWrapperItem messageJsonWrapperItem = new DialogJsonWrapperItem(jsonObject);
            //final DialogJsonWrapperItem dialogListWrapperItem = new DialogJsonWrapperItem();
            iDialogItems.add(messageJsonWrapperItem);
            //} catch (JSONException e) {
            //    e.printStackTrace();
            //}
        }

        return iDialogItems;
    }

    /*@Override
    public List<VKApiMessage> getListDialogs() {
        final List<DialogJsonWrapper> messageJsonWrappers = new ArrayList<>();
        for (int i = 0; i < mJsonArray.length(); i++) {
            try {
                final JSONObject jsonObject = mJsonArray.getJSONObject(i);
                final DialogJsonWrapper messageJsonWrapper = new DialogJsonWrapper(jsonObject);
                messageJsonWrappers.add(messageJsonWrapper);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        List<VKApiMessage> iAlls = new ArrayList<>();
        iAlls.addAll(messageJsonWrappers);
        return iAlls;
    }*/
}