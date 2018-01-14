package com.github.pavelkv96.vksdk_library.model.dialogs;

public interface IDialogItem {
    int unread();
    IDialog idialog();
    long in_read();
    long out_read();
}
