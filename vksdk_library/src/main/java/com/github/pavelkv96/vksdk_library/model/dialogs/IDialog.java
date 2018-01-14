package com.github.pavelkv96.vksdk_library.model.dialogs;

import com.github.pavelkv96.vksdk_library.model.messages.VKApiMessage;

public interface IDialog extends VKApiMessage {
    //VKApiMessage vk_api_message();

    String title();

    int users_count();

    long admin_id();

    String photo_50();
}
