package com.github.pavelkv96.vksdk_library.model.messages;

import java.io.Serializable;

public interface VKApiMessage extends Serializable {

    int id();

    int user_id();

    long date();

    boolean read_state();

    boolean out();

    String body();

    //public VKAttachments attachments = new VKAttachments();
    String fwd_messages();

    int chat_id();
}
