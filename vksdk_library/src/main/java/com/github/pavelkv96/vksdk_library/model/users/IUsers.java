package com.github.pavelkv96.vksdk_library.model.users;

import java.io.Serializable;

public interface IUsers extends Serializable{
    long id();

    String first_name();

    String last_name();

    String photo_50();

    int is_friend();

    int online();

    IUserLastSeen last_seen();
}
